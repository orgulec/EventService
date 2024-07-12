package thyme.event_service.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.event.EventModel;
import thyme.event_service.subscriptions.SubscriptionModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    private String username;
    private String firstname;
    private String lastname;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private String role;
    private Integer age;

    @OneToMany(mappedBy = "owner")
    private Set<EventModel> myEvents;

    @OneToMany(mappedBy = "author")
    private List<CommentsModel> comments;

    @OneToMany(mappedBy = "subscriber")
    private List<SubscriptionModel> subscriptions;


    public String getUserData(){
        return "Nick: "+ username +
                "\nFirst name: " + firstname +
                "\nLast name: " + lastname +
                "\nE-mail: " + email +
                "\n";
    }

    @Override
    public String toString() {
        return username + " (" + firstname + " " + lastname + ")";
    }

}
