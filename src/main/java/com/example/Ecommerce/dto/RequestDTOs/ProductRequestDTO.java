package com.example.Ecommerce.dto.RequestDTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    @NotBlank(message = "productName is required")
    private String name;

    @NotBlank(message = "description is required")
    @Size(min = 20, max = 500, message = "description about the product should atleast between 20 to 500")
    private String description;

    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "stock cannot be negative")
    private int stock;

    private long categoryId;

    public ProductRequestDTO(){

    }

    public ProductRequestDTO(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
