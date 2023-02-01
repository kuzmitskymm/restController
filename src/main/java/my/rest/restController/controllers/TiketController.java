package my.rest.restController.controllers;


import my.rest.restController.entity.Tiket;
import my.rest.restController.entity.User;
import my.rest.restController.models.Message;
import my.rest.restController.services.TiketService;
import my.rest.restController.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/v1/tiket")
public class TiketController {
    private final TiketService tiketService;
    private final UserService userService;

    @Autowired
    public TiketController(TiketService tiketService, UserService userService) {
        this.tiketService = tiketService;
        this.userService = userService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Tiket>> readAll(){
        List<Tiket> tikets = tiketService.readAll();
        return tikets != null
                ? new ResponseEntity<>(tikets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{id}")
    public Object getTiketById(@PathVariable(value = "id") long id) {

        try{
            var tiket = tiketService.read(id);
            return new ResponseEntity<>(tiket,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public Object createTiket(@RequestHeader String login, @RequestHeader String password, @RequestBody Tiket tiket) {

        try{
            User user = userService.getUser(login, password);
            if(user == null){
                Message msg = new Message("AUTHORIZATION_FAILED", "Авторизация прошла с ошибкой. Новый тикет не получилось создать.");
                return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
            }

            tiketService.create(tiket);
            return new ResponseEntity<>(tiket,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Object removeTiketById(@RequestHeader String login, @RequestHeader String password, @PathVariable(value = "id") long id){
        try{
            User user = userService.getUser(login, password);
            if(user == null){
                Message msg = new Message("AUTHORIZATION_FAILED", "Авторизация прошла с ошибкой. Новый тикет не получилось создать.");
                return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
            }

            if(tiketService.delete(id)){
                return new ResponseEntity<>(id,HttpStatus.OK);
            }
            else{
                Message msg = new Message("DELETE_WRONG", "Тикет не удалился.");
                return new ResponseEntity<>(msg,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/{id}/{days}")
    public Object updateTiket(@RequestHeader String login, @RequestHeader String password,
                              @PathVariable(value = "id") long id, @PathVariable(value = "days") long days) {

        try{
            User user = userService.getUser(login, password);
            if(user == null){
                Message msg = new Message("AUTHORIZATION_FAILED", "Авторизация прошла с ошибкой. Новый тикет не получилось создать.");
                return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
            }

            tiketService.update(id,days);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
