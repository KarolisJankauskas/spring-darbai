package lt.techin.demo.dto;

import java.util.List;

public record UserResponseDTO(Long id, String username, List<String> roles) {
}

