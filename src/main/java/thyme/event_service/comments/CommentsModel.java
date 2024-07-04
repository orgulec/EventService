package thyme.event_service.comments;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.event.EventModel;
import thyme.event_service.user.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class CommentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel author;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventModel event;

    private String message;
    private Integer rating;
    private LocalDateTime date;

    public String getDateFormatted(){
        return date.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yy"));
    }

}
