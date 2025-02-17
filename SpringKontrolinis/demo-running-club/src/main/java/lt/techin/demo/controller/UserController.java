package lt.techin.demo.controller;

import jakarta.validation.Valid;
import lt.techin.demo.dto.RegistrationRequestDTO;
import lt.techin.demo.dto.RegistrationResponseDTO;
import lt.techin.demo.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  @PostMapping("/create")
  public String createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

    return "User created successfully!";
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
    return null;
  }

  @GetMapping("/registrations/{id}")
  public RegistrationResponseDTO getRegistration(@PathVariable Long id) {
    UserController registrationService = new UserController();
    return registrationService.getRegistration(id);
  }


}
