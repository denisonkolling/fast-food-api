package com.fastfood.service.impl;

import com.fastfood.model.Product;
import com.fastfood.repository.ProductRepository;
import com.fastfood.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Product createProduct(Product product) {
        boolean productExists = productRepository.existsById(product.getNumber());
        if (productExists){
            throw new IllegalArgumentException("Product already created");
        }
        product = productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = productRepository.findById(product.getNumber()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        mapper.map(product, productDB);
        productDB = productRepository.save(productDB);
        return productDB;
    }

    @Override
    public void deleteProduct(Integer id) {
        boolean productExists = productRepository.existsById(id);
        if (!productExists){
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }


}
