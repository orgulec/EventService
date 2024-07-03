package thyme.event_service;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nickname;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;

    @OneToMany
    private List<CommentsModel> comments;
    @OneToMany
    private Set<EventModel> events;

    @Override
    public String toString() {
        return nickname + " (" + firstname + " " + lastname + ")";
    }
}
