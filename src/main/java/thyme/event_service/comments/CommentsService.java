package thyme.event_service.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
}
