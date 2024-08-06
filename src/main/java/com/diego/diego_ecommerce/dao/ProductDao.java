package com.diego.diego_ecommerce.dao;

import com.diego.diego_ecommerce.models.ProductModel;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;



public interface ProductDao extends ListCrudRepository<ProductModel,Long> {
    Optional<ProductModel> findByNameIgnoreCase(String name);
}
