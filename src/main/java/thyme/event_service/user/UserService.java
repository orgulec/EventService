package thyme.event_service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserModel getById(long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException("User not found.");
        }
        return user.get();
    }
}
