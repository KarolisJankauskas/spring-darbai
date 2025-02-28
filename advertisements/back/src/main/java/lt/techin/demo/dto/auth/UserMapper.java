package lt.techin.demo.dto.auth;

import lt.techin.demo.model.Role;
import lt.techin.demo.model.User;

public class UserMapper {

    public static RegisterResponseDTO toRegisterResponseDTO(User user) {
        return new RegisterResponseDTO(
                user.getUsername(),
                user.getEmail(), // Added email
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
    }

    public static LoginResponseDTO toLoginResponseDTO(User user) {
        return new LoginResponseDTO(
                user.getUsername(),
//                user.getEmail(), // Added email
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
    }
}