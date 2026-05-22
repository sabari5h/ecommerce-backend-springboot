package com.example.Ecommerce.dto.RequestDTOs;


import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required")
    private String categoryName;

    public CategoryRequestDTO(){

    }

    public CategoryRequestDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
