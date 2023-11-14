package com.fastfood.service;

import com.fastfood.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> findAllProducts();

    Product findProductById(Integer id);

}
