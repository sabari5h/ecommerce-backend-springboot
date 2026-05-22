package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.ProductNotFound;
import com.example.Ecommerce.dto.RequestDTOs.ProductRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.ProductResponseDTO;
import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public String addProduct(ProductRequestDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());
        if (productDTO.getCategoryId() > 0){
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new ProductNotFound("Category not found"));
            product.setCategory(category);
        }
        productRepository.save(product);
        return "Product added successfully";
    }
    public List<ProductResponseDTO> getAllProducts(){
        List<Product> product1 = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : product1){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(product.getId());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setDescription(product.getDescription());
            productResponseDTO.setPrice(product.getPrice());
            productResponseDTO.setStock(product.getStock());
            productResponseDTO.setCategoryName(product.getCategory() != null ? product.getCategory().getCategory_name() : null);

            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }
    @Transactional
    public String updateProduct(long id, ProductRequestDTO updatedProduct){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product not found"));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        if (updatedProduct.getCategoryId() > 0){
            Category category = categoryRepository.findById(updatedProduct.getCategoryId())
                    .orElseThrow(() -> new ProductNotFound("category not found"));
            product.setCategory(category);
        }
        productRepository.save(product);
        return "updated successfully";
    }
    @Transactional
    public String deleteProduct(long id){
        productRepository.deleteById(id);
        return "product deleted successfully";
    }
    public ProductResponseDTO getProduct(long id){
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("no such product exist"));
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setCategoryName(product.getCategory() != null ? product.getCategory().getCategory_name() : null);
        return productResponseDTO;
    }
    public List<ProductResponseDTO> getProductByname(String name){
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : products){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(product.getId());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setDescription(product.getDescription());
            productResponseDTO.setPrice(product.getPrice());
            productResponseDTO.setStock(product.getStock());
            productResponseDTO.setCategoryName(product.getCategory() != null ? product.getCategory().getCategory_name() : null);

            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }
}
