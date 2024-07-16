package thyme.event_service.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CommentsRepository extends JpaRepository<CommentsModel,Long> {

    List<CommentsModel> findAllByEvent_Id(Long eventId);

    List<CommentsModel> findAllByAuthor_Id(Long userId);
}
