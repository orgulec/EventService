package thyme.event_service.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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
    @FutureOrPresent(message = "Date must be from future.")
    LocalDateTime date;
    @PositiveOrZero(message = "Price should by 0 or higher")
    Double cost = 0.0;

    Boolean active;
}
