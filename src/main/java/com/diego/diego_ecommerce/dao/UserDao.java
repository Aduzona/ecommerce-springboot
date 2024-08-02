package com.diego.diego_ecommerce.dao;

import com.diego.diego_ecommerce.models.DiegoUserModel;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDao extends ListCrudRepository<DiegoUserModel, Long> {

    Optional<DiegoUserModel> findByUsernameIgnoreCase(String username);
    Optional<DiegoUserModel> findByEmailIgnoreCase(String email);
}
