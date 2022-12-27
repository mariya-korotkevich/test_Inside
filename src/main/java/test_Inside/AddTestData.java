package test_Inside;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import test_Inside.models.entity.User;
import test_Inside.repositories.UserRepository;

import java.util.Optional;

@Component
public class AddTestData {
    private final UserRepository userRepository;

    public AddTestData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createUser() {
        Optional<User> optionalUser = userRepository.findByName("admin");
        if (optionalUser.isEmpty()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setName("admin");
            user.setPassword(encoder.encode("admin"));
            userRepository.save(user);
        }
    }
}
