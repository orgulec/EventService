package thyme.event_service;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    private UserModel owner;

    private String title;
    private String description;
    private ZonedDateTime date;
    @Embedded
    private Address address;
    private Double cost;
    private boolean active;

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm"));
    }
}
