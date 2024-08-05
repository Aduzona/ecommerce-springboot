package com.diego.diego_ecommerce.dao;

import com.diego.diego_ecommerce.models.ProductModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;



public interface ProductDao extends CrudRepository<ProductModel,Long> {
    Optional<ProductModel> findByNameIgnoreCase(String name);
}
