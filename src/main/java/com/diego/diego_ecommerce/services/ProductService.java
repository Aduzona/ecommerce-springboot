package com.diego.diego_ecommerce.services;

import com.diego.diego_ecommerce.dao.ProductDao;
import com.diego.diego_ecommerce.dto.ResponseDiego;
import com.diego.diego_ecommerce.models.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ResponseDiego addProduct(ProductModel productInfo){

        ResponseDiego response = new ResponseDiego();

        if(productDao.findByNameIgnoreCase(productInfo.name).isPresent()){
            response.message     = "A Product with name "+ productInfo.name + " already exists";
            return response;
        }

        if(productInfo.name.isEmpty() ||  productInfo.name == null){
            response.message = "Price should not be empty";
            return response;
        }

        if(productInfo.price == null){
            response.message = "Price should not be empty";
            return response;
        }

        productDao.save(productInfo);
        response.message = "Product successfully added";

        return response;

    }

    public List<ProductModel> getProducts(){
        return productDao.findAll();
    }

    public ProductModel getOneProduct(Long id){
        Optional<ProductModel> product = productDao.findById(id);
        if(product.isPresent()){
            return  product.get();
        }else{
            return null;
        }
    }
}
