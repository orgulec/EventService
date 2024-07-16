package thyme.event_service.subscriptions;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.event.EventModel;
import thyme.event_service.user.UserModel;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class SubscriptionModel {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="subscriber_id")
    private UserModel subscriber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="event_id")
    private EventModel event;

}
