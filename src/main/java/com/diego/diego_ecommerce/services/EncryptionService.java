package com.diego.diego_ecommerce.services;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {


    @Value("${encryption.salt.rounds}") // Do not forget to define this in application properties
    private int saltRounds;

    private String salt;


    @PostConstruct
    public void postConstruct(){
        salt = BCrypt.gensalt(saltRounds);
    }



    public String passwordEncrypt(String password){
        return BCrypt.hashpw(password, salt);
    }
}
