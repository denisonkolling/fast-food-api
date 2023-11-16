package com.fastfood.controller;

import com.fastfood.dto.ProductRequest;
import com.fastfood.dto.ProductResponse;
import com.fastfood.model.Product;
import com.fastfood.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        List<Product> products = productService.findAllProducts();
        var response = products
                .stream()
                .map(product -> mapper.map(product,ProductResponse.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("id") Integer id) {
        var product = productService.findProductById(id);
        var response = mapper.map(product, ProductResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        var product = mapper.map(request, Product.class);
        product = productService.createProduct(product);
        var response = mapper.map(product, ProductResponse.class);
        return ResponseEntity.created(URI.create(product.getNumber().toString())).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody @Valid ProductRequest request){
        var product = mapper.map(request, Product.class);
        product.setNumber(id);
        product = productService.updateProduct(product);
        var response = mapper.map(product, ProductResponse.class);
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
