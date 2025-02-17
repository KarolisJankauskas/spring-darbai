package lt.techin.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotNull(message = "Username cannot be null")
        @Pattern(regexp = "^[a-z0-9]{4,}$", message = "Username must be lowercase letters (numbers allowed) and at least 4 characters long")
        String username,

        @NotNull(message = "Password cannot be null")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,

        @NotNull(message = "Roles cannot be null")
        String[] roles
) {
}

