package thyme.event_service.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thyme.event_service.dto.NewCommentDto;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserService userService;

    public List<CommentsModel> findByEventId(Long id) {
        List<CommentsModel> comments = commentsRepository.findAllByEvent_Id(id);
//        if(comments.isEmpty()){
//            throw new NoSuchElementException("No comments founded.");
//        }
        return comments;
    }

    public CommentsModel addComment(NewCommentDto newComment) {

        UserModel user = userService.getById(1L);//todo authorisation & authentication

        CommentsModel comment = new CommentsModel();
        comment.setAuthor(user);
        comment.setEvent(newComment.getEvent());
        comment.setMessage(newComment.getMessage());
        comment.setRating(newComment.getRating());
        comment.setDate(LocalDateTime.now());

        return commentsRepository.save(comment);
    }
}
