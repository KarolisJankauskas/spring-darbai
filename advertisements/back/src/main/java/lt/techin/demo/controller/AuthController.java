package lt.techin.demo.controller;

import jakarta.validation.Valid;
import lt.techin.demo.dto.auth.LoginResponseDTO;
import lt.techin.demo.dto.auth.RegisterRequestDTO;
import lt.techin.demo.dto.auth.RegisterResponseDTO;
import lt.techin.demo.dto.auth.UserMapper;
import lt.techin.demo.model.User;
import lt.techin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        User newUser = userService.addUser(
                registerRequestDTO.username(),
                registerRequestDTO.email(),
                registerRequestDTO.password()
        );

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .replacePath("/api/users/{id}")
                                .buildAndExpand(newUser.getId())
                                .toUri())
                .body(UserMapper.toRegisterResponseDTO(newUser));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponseDTO> loginUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(UserMapper.toLoginResponseDTO(user));
    }
}