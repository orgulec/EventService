package thyme.event_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import thyme.event_service.event.EventModel;
import thyme.event_service.user.UserModel;

import java.time.LocalDateTime;

@Data
public class NewCommentDto {
    @Range(min = 0, max = 10L, message = "Rating should be from 1 to 10")
    Integer rating = 0;
    @NotBlank(message = "Message is necessary")
    String message;
    LocalDateTime date;
    EventModel event;
    UserModel author;
}
