package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDTOs.ProductRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.ProductResponseDTO;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public String addProduct(@Valid @RequestBody ProductRequestDTO product){
        return productService.addProduct(product);
    }
    @GetMapping("")
    public List<ProductResponseDTO> getAllProduct(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable long id,@Valid @RequestBody ProductRequestDTO product){
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }
    @GetMapping("/{name}/name")
    public List<ProductResponseDTO> getProductByName(@PathVariable String name){
        return productService.getProductByname(name);
    }
}
