package thyme.event_service.subscriptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import thyme.event_service.event.EventModel;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserRepository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public List<SubscriptionModel> getAllByEvent(long eventId) {
        List<SubscriptionModel> subscription = subscriptionRepository.findAllByEvent_Id(eventId);
        return subscription;
    }
    public List<SubscriptionModel> getAllByUser(long userId) {
        List<SubscriptionModel> subscription = subscriptionRepository.findAllBySubscriber_Id(userId);
        return subscription;
    }

    public Optional<SubscriptionModel> getByEventAndUser(EventModel event, UserModel user){
        return subscriptionRepository.findByEvent_IdAndSubscriber_Id(event.getId(), user.getId());
    }

    public void createSubscription(EventModel event, UserModel user) {
        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setEvent(event);
        subscription.setSubscriber(user);
        subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(EventModel event, UserModel user) {
        Optional<SubscriptionModel> subscription = getByEventAndUser(event, user);
        subscription.ifPresent(subscriptionRepository::delete);
    }


}
