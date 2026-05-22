package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDTOs.CategoryRequestDTO;
import com.example.Ecommerce.dto.ResponseDTOs.CategoryResponseDTO;
import com.example.Ecommerce.dto.ResponseDTOs.ProductResponseDTO;
import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public String addCategory( @Valid @RequestBody CategoryRequestDTO category){
        categoryService.addCategory(category);
        return "category added successfully";
    }
    @GetMapping("")
    public List<CategoryResponseDTO> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public List<ProductResponseDTO> getProductsByCategory(@PathVariable long id){
        return categoryService.getAllProductsById(id);
    }
}
