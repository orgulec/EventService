package thyme.event_service.event;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.user.UserModel;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel owner;

    private String title;
    private String description;
    private LocalDateTime date;
    @Embedded
    private Address address;
    private Double cost;
    private boolean active;

    @OneToMany(mappedBy = "event")
    private List<CommentsModel> comments;// = new ArrayList<>();

    @ManyToMany(mappedBy = "subscriptions")
    private Set<UserModel> subscribers;// = new HashSet<>();


    public String getDateFormatted(){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
    }

    public Long getOwnerId(){
        return owner.getId();
    }


}
