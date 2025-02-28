package lt.techin.demo.dto.auth;

import java.util.List;

public record LoginResponseDTO(String username, List<String> roles) {
}
