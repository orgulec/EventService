package thyme.event_service.user;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.event.EventModel;
import thyme.event_service.subscriptions.SubscriptionModel;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nickname;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Integer age;

    @OneToMany(mappedBy = "owner")
    private Set<EventModel> myEvents;

    @OneToMany(mappedBy = "author")
    private List<CommentsModel> comments;

    @OneToMany(mappedBy = "subscriber")
    private List<SubscriptionModel> subscriptions;

    public String getUserData(){
        return "Nick: "+nickname +
                "\nFirst name: " + firstname +
                "\nLast name: " + lastname +
                "\nE-mail: " + email +
                "\n";
    }

    @Override
    public String toString() {
        return nickname + " (" + firstname + " " + lastname + ")";
    }

}
