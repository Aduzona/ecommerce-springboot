package com.diego.diego_ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class DiegoUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "firstname")
    public String firstName;

    @Column(name = "lastname")
    public String lastName;

    @Column(name = "username", nullable = false, unique = true)
    public String username;

    @Column(name = "password", nullable = false, length = 1000)
    public String password;

    @Column(name = "email", unique = true, nullable = false, length = 500)
    public String email;


}
