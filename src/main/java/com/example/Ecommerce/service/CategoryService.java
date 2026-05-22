package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDTOs.CategoryRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.CategoryResponseDTO;
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
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public String addCategory(CategoryRequestDTO categoryDTO){
        Category category = new Category();
        category.setCategory_name(categoryDTO.getCategoryName());
        categoryRepository.save(category);
        return "done";
    }

    public List<CategoryResponseDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        for (Category category : categories){
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(category.getCategoryId());
            categoryResponseDTO.setCategoryName(category.getCategory_name());
            categoryResponseDTOS.add(categoryResponseDTO);
        }
        return categoryResponseDTOS;
    }
    public List<ProductResponseDTO> getAllProductsById(long id){
         List<Product> products = productRepository.findByCategory_CategoryId(id);
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
