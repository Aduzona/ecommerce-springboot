package com.diego.diego_ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "description", length = 1000)
    public String description;

    @Column(name = "price", nullable = false)
    public Integer price;

    @Column(name = "imageUrl", length = 500)
    public String imageUrl;

}
