package thyme.event_service.user;

import jakarta.persistence.*;
import lombok.*;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.event.EventModel;

import java.util.ArrayList;
import java.util.HashSet;
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
    private Set<EventModel> myEvents;// = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private List<CommentsModel> comments;// = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)//, mappedBy = "subscribers")
    private Set<EventModel> subscriptions;// = new HashSet<>();

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
