package thyme.event_service.event;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.SortedSetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thyme.event_service.dto.NewEventDto;
import thyme.event_service.exceptions.WrongInputDtoException;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserRepository;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    public List<EventModel> getAll() {
        return new ArrayList<>(eventRepository.findAll());
    }

    public EventModel getById(long id) {
        Optional<EventModel> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EntityNotFoundException("Event not found");
        }
        return event.get();
    }

    public EventModel signMeIn(long eventId) {
        EventModel event = getById(eventId);

        UserModel user = userRepository.findById(1l).get(); //todo authorisation & authentication

        if(!event.getSubscribers().contains(user)) {
            event.getSubscribers().add(user);
            user.getSubscriptions().add(event);
        } else {
            event.getSubscribers().remove(user);
            user.getSubscriptions().remove(event);
        }
//        userRepository.save(user);
        return eventRepository.saveAndFlush(event);
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

    public EventModel addEvent(NewEventDto eventDto) {

        Address address = new Address(eventDto.getCity(), eventDto.getStreet(), eventDto.getNumber(), eventDto.getCountry());
        EventModel newEvent = new EventModel();

        UserModel user = userRepository.findById(1l).get();
        newEvent.setOwner(user); //todo authorisation & authentication

            newEvent.setAddress(address);
            newEvent.setTitle(eventDto.getTitle());
            newEvent.setDescription(eventDto.getDescription());
            newEvent.setDate(eventDto.getDate());
            newEvent.setCost(eventDto.getCost());
            newEvent.setActive(eventDto.getActive());

            newEvent.setComments(new ArrayList<>());
            newEvent.setSubscribers(new HashSet<>());

        user.getMyEvents().add(newEvent);
        userRepository.save(user);

        return eventRepository.save(newEvent);
    }
}
