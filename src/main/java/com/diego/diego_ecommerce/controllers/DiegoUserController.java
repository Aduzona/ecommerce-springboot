package com.diego.diego_ecommerce.controllers;


import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.DiegoUserModel;
import com.diego.diego_ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class DiegoUserController {

    private UserService userService;

    public DiegoUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity createNewUser(@RequestBody DiegoUserModel userInfo)  {
        return ResponseEntity.ok(userService.addUser(userInfo));
    }

    @GetMapping()
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Long id){
        ResponseDiego response = new ResponseDiego();

        DiegoUserModel user = userService.getOneUser(id);

        if(user == null){
            response.message = "User with id "+ id + " does not exist.";
            return  ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(user);
    }
}
