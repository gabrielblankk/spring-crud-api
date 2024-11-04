package com.blankk.springapi.controllers;

import com.blankk.springapi.domain.product.Product;
import com.blankk.springapi.domain.product.ProductDTO;
import com.blankk.springapi.domain.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();

        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid ProductDTO data) {
        repository.save(new Product(data));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTO data, @PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setName(data.name());
            product.setPrice(data.price());

            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            repository.delete(product);

            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
