package my.rest.restController.services;

import my.rest.restController.entity.User;
import my.rest.restController.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(User user) {
        User answer = null;
        try {
            answer = userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer == null ? false : true;
    }

    @Override
    public User getUser(String login, String password) {
        User answer = null;
        try {
            answer = userRepository.findByLoginAndPassword(login, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}
