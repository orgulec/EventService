package thyme.event_service.subscriptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thyme.event_service.event.EventModel;
import thyme.event_service.user.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, Long> {

    public List<SubscriptionModel> findAllByEvent_Id(Long id);

    public List<SubscriptionModel> findAllBySubscriber_Id(Long id);

    public List<SubscriptionModel> findAllByEventAndSubscriber(EventModel event, UserModel user);
    public Optional<SubscriptionModel> findByEvent_IdAndSubscriber_Id(long eventId, long userId);

}
