package my.rest.restController.services;

import my.rest.restController.entity.User;

public interface UserService {

    boolean create(User user);

    User getUser(String login, String password);
}
