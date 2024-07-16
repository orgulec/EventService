package thyme.event_service.event;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import thyme.event_service.dto.NewEventDto;
import thyme.event_service.subscriptions.SubscriptionModel;
import thyme.event_service.subscriptions.SubscriptionService;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SubscriptionService subscriptionService;

    public List<EventModel> getAll() {
        return new ArrayList<>(eventRepository.findAll());
    }

    public EventModel getById(long id) {
        Optional<EventModel> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EntityNotFoundException("Event not found (id: " + id + ").");
        }
        return event.get();
    }

    public List<EventModel> getAllByUser(Long userId) {
//        Optional<UserModel> userOpt = userRepository.findById(userId);
//        if(userOpt.isEmpty()){
//            throw new EntityNotFoundException("User not found (id: " + userId + ").");
//        }
//        UserModel user = userOpt.get();
        return eventRepository.findAllByOwner_Id(userId);
    }

    public EventModel signMeIn(long eventId, String username) {
        EventModel event = getById(eventId);
        UserModel user = getUserIfExists(username);
        Optional<SubscriptionModel> subscription = subscriptionService.getByEventAndUser(event, user);

        if (subscription.isEmpty()) {
            subscriptionService.createSubscription(event, user);
        } else {
            subscriptionService.deleteSubscription(event, user);
        }
        return eventRepository.save(event);
    }

    public List<EventModel> getByText(String toFind) {
        String find = toFind.toLowerCase();
        List<EventModel> foundedEvents = getAll().stream()
                .filter(a -> a.getTitle().toLowerCase().contains(find) ||
                        a.getDescription().toLowerCase().contains(find) ||
                        a.getAddress().getStreet().toLowerCase().contains(find) ||
                        a.getAddress().getCountry().toLowerCase().contains(find) ||
                        a.getAddress().getCity().toLowerCase().contains(find))
                .collect(Collectors.toList());

        if (foundedEvents.isEmpty()) {
            throw new NoSuchElementException("No Events founded");
        }
        return foundedEvents;
    }

    public EventModel addEvent(NewEventDto eventDto, String username) {

        Address address = new Address(eventDto.getCity(), eventDto.getStreet(), eventDto.getNumber(), eventDto.getCountry());
        EventModel newEvent = new EventModel();

        UserModel user = getUserIfExists(username);
        newEvent.setOwner(user);

        newEvent.setAddress(address);
        newEvent.setTitle(eventDto.getTitle());
        newEvent.setDescription(eventDto.getDescription());
        newEvent.setDate(eventDto.getDate());
        newEvent.setCost(eventDto.getCost());
        newEvent.setActive(eventDto.getActive());

        newEvent.setComments(new ArrayList<>());
        newEvent.setSubscriptions(new ArrayList<>());

        user.getMyEvents().add(newEvent);
        userRepository.save(user);

        return eventRepository.save(newEvent);
    }

    private UserModel getUserIfExists(String username) {
        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        return userOpt.get();
    }

}
