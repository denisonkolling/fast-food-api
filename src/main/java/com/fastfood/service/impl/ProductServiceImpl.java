package com.fastfood.service.impl;

import com.fastfood.model.Product;
import com.fastfood.repository.ProductRepository;
import com.fastfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Product findProductById(Integer id) {
        return null;
    }
}
