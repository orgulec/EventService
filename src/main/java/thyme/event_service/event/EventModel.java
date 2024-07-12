package thyme.event_service.event;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.subscriptions.SubscriptionModel;
import thyme.event_service.user.UserModel;

import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private UserModel owner;

    private String title;
    private String description;
    private LocalDateTime date;
    @Embedded
    private Address address;
    private Double cost;
    private boolean active;

    @OneToMany(mappedBy = "event")
    private List<CommentsModel> comments;

    @OneToMany(mappedBy = "event")
    private List<SubscriptionModel> subscriptions;


    public String getDateFormatted(){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
    }

    public Long getOwnerId(){
        return owner.getId();
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "id=" + id +
                ", owner=" + owner.getUsername() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", address=" + address +
                ", cost=" + cost +
                ", active=" + active +
                '}';
    }
}
