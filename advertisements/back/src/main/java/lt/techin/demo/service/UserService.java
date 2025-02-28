package lt.techin.demo.service;

import lt.techin.demo.exception.GenericValidationException;
import lt.techin.demo.model.Role;
import lt.techin.demo.model.User;
import lt.techin.demo.repository.RoleRepository;
import lt.techin.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  public User addUser(String username, String email, String password) {
    Optional<User> maybeDuplicateUser = userRepository.findByUsername(username);
    if (maybeDuplicateUser.isPresent()) {
      throw new GenericValidationException("User with username \"" + username + "\" already exists.");
    }

    Optional<User> maybeDuplicateEmail = userRepository.findByEmail(email);
    if (maybeDuplicateEmail.isPresent()) {
      throw new GenericValidationException("User with email \"" + email + "\" already exists.");
    }

    Role defaultRole = roleRepository.findByName("ROLE_USER");
    User user = new User(username, email, passwordEncoder.encode(password), List.of(defaultRole));
    return userRepository.save(user);
  }

  public User saveUser(User user) {
    if (Objects.nonNull(user.getId())) {
      Optional<User> existingUser = userRepository.findById(user.getId());
      if (existingUser.isPresent()) {
        User currentUser = existingUser.get();
        if (!currentUser.getEmail().equals(user.getEmail())) {
          Optional<User> userWithSameEmail = userRepository.findByEmail(user.getEmail());
          if (userWithSameEmail.isPresent()) {
            throw new GenericValidationException("Email \"" + user.getEmail() + "\" is already in use.");
          }
        }
      }
    }
    return userRepository.save(user);
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }
}