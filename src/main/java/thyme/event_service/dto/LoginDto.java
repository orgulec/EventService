package thyme.event_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "You must enter username")
    private String username;
    @NotBlank(message = "You must enter password")
    private String password;
}
