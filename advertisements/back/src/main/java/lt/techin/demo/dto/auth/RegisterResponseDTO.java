package lt.techin.demo.dto.auth;

import java.util.List;

public record RegisterResponseDTO(
        String username,
        String email,
        List<String> roles
) {}