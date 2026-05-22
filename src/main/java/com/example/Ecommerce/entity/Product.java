package com.example.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_name", nullable = false)
    @NotBlank(message = "productName is required")
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "description is required")
    @Size(min = 20, max = 500, message = "description about the product should atleast between 20 to 500")
    private String description;

    @Min(value = 0, message = "stock cannot be negative")
    @Column(name = "stock", nullable = false)
    private int stock;

    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    @Column(name = "price", nullable = false)
    private double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    @JsonSetter("category")
    public void setCategory(Category category) {
        this.category = category;
    }
}
