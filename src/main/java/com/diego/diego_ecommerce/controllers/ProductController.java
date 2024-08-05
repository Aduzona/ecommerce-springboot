package com.diego.diego_ecommerce.controllers;

import com.diego.diego_ecommerce.dao.ProductDao;
import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * create a ProductController. Inside this controller, create a method called addProduct.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao=productDao;
    }

    @PostMapping("/add")
    ResponseEntity addProduct(@RequestBody ProductModel productInfo) throws Exception{

        ResponseDiego response = new ResponseDiego();

        if(productDao.findByNameIgnoreCase(productInfo.name).isPresent()){
            response.message     = "A Product with name "+ productInfo.name + " already exists";
            return ResponseEntity.ok(response);
        }

        if(productInfo.name.isEmpty() ||  productInfo.name == null){
            response.message = "Price should not be empty";
            return ResponseEntity.ok(response);
        }

        if(productInfo.price == null){
            response.message = "Price should not be empty";
            return ResponseEntity.ok(response);
        }

        productDao.save(productInfo);

        return ResponseEntity.ok("Product successfully Added");
    }
}
