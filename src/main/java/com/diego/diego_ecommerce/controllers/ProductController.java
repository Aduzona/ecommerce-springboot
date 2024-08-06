package com.diego.diego_ecommerce.controllers;

import com.diego.diego_ecommerce.dao.ProductDao;
import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.ProductModel;
import com.diego.diego_ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * create a ProductController. Inside this controller, create a method called addProduct.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    ResponseEntity createNewProduct(@RequestBody ProductModel productInfo) throws Exception{
        return ResponseEntity.ok(productService.addProduct(productInfo));
    }

    @GetMapping()
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public  ResponseEntity getOneProduct(@PathVariable Long id ){
        ResponseDiego response = new ResponseDiego();

        ProductModel product = productService.getOneProduct(id);

        if(product == null){
            response.message = "Product with id "+ id + " does not exist.";
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.ok(product);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductModel newProductElement){
        return ResponseEntity.ok(productService.updateProduct(id, newProductElement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
