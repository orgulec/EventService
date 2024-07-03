package thyme.event_service.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsModel,Long> {
//    List<CommentsModel> findAllByEventModel_Id(Long id);
    List<CommentsModel> findAllByEvent_Id(Long id);
}
