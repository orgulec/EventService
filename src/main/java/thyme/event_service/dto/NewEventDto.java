package thyme.event_service.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewEventDto {
    @NotBlank(message = "Title is necessary")
    String title;
    @NotBlank(message = "Description is necessary")
    String description;
    @NotBlank(message = "Country is necessary")
    String country;
    @NotBlank(message = "City is necessary")
    String city;
    String street = "";
    String number = "";
    @Future(message = "Date must be from future")
    @NotNull(message = "You have to choose date")
    LocalDateTime date;
    @PositiveOrZero(message = "Price should by 0 or higher")
    Double cost = 0.0;
    @NotNull(message = "You have to choose Yes/No")
    Boolean active;
}
