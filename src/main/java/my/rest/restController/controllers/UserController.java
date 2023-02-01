package my.rest.restController.controllers;

import my.rest.restController.entity.User;
import my.rest.restController.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/v1/tiket")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public Object createUser(@RequestBody User user) {
        try{
            userService.create(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public Object getUserByLoginAndPassword(@RequestHeader String login, @RequestHeader String password) {

        try{
            var user = userService.getUser(login, password);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
