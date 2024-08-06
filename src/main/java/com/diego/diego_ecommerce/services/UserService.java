package com.diego.diego_ecommerce.services;


import com.diego.diego_ecommerce.dao.UserDao;
import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.DiegoUserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;
    private EncryptionService crypto;

    public UserService(UserDao userDao, EncryptionService crypto) {
        this.userDao = userDao;
        this.crypto = crypto;
    }

    public  ResponseDiego addUser(DiegoUserModel userInfo){

        ResponseDiego response = new ResponseDiego();

        if(userDao.findByUsernameIgnoreCase(userInfo.username).isPresent()){
            response.message     = "A user with username "+ userInfo.username + " already exists";
            return response;
        }

        if(userDao.findByEmailIgnoreCase(userInfo.email).isPresent()){
            response.message = "A user with email "+ userInfo.email + " already exists";
            return response;
        }

        DiegoUserModel user = new DiegoUserModel();
        user.firstName = userInfo.firstName;
        user.lastName = userInfo.lastName;

        if(userInfo.username == "" ||  userInfo.username == null){
            response.message = "Username should not be empty";
            return response;
        }else{
            user.username = userInfo.username;
        }

        if(userInfo.email == "" ||  userInfo.email == null){
            response.message = "Email should not be empty";
            return response;
        }else{
            user.email = userInfo.email;
        }

        user.password = crypto.passwordEncrypt(userInfo.password);

        userDao.save(user);

        response.message = "User successfully added";
        return response;
    }


    public List<DiegoUserModel> getUsers(){
       return userDao.findAll();
    }

    public DiegoUserModel getOneUser(Long id){
        Optional<DiegoUserModel> user =  userDao.findById(id);

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
}
