package com.diego.diego_ecommerce.controllers;


import com.diego.diego_ecommerce.dao.UserDao;
import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.DiegoUserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class DiegoUserController {

    private UserDao userDao;

    public DiegoUserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/register")
    ResponseEntity createNewUser(@RequestBody DiegoUserModel userInfo) throws Exception {

        ResponseDiego response = new ResponseDiego();

        if(userDao.findByUsernameIgnoreCase(userInfo.username).isPresent()){
            response.message     = "A user with username "+ userInfo.username + " already exists";
            return ResponseEntity.ok(response);
        }

        if(userDao.findByEmailIgnoreCase(userInfo.email).isPresent()){
            response.message     = "A user with email "+ userInfo.email + " already exists";
            return ResponseEntity.ok(response);
        }

        DiegoUserModel user = new DiegoUserModel();
        user.firstName = userInfo.firstName;
        user.lastName = userInfo.lastName;

        if(userInfo.username == "" ||  userInfo.username == null){
            response.message = "Username should not be empty";
           return ResponseEntity.ok(response);
        }else{
            user.username = userInfo.username;
        }

        if(userInfo.email == "" ||  userInfo.email == null){
            response.message = "Email should not be empty";
            return ResponseEntity.ok(response);
        }else{
            user.email = userInfo.email;
        }

        // TODO: encrypt the password before saving it in the database
        user.password = userInfo.password;

        userDao.save(user);

        return ResponseEntity.ok("User successfully Added");
    }
}
