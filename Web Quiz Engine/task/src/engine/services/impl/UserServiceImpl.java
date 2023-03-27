package engine.services.impl;

import engine.models.User;
import engine.repositories.UserRepository;
import engine.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) throws IllegalAccessException {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isEmpty()) {
            User userToSave = User.builder().email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .build();
            userRepository.save(userToSave);
        } else {
            throw new IllegalAccessException();
        }
    }
}
