package lt.techin.demo.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.demo.model.User;

public class RegistrationRequestDTO {
  @NotNull(message = "User cannot be null")
  private User user;

}
