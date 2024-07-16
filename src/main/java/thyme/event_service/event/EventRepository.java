package thyme.event_service.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thyme.event_service.user.UserModel;

import java.util.List;

@Repository
interface EventRepository extends JpaRepository<EventModel, Long> {

    @Query(value = "SELECT e FROM EventModel e WHERE e.title LIKE :text OR e.description LIKE :text")
    List<EventModel> findAllByTextQuery(String text);

    List<EventModel> findAllByOwner_Id(Long ownerId);
}
