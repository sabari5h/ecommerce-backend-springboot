package com.example.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @NotBlank(message = "category name is required")
    @Column(name = "category_name", nullable = false)
    private String category_name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    @JsonIgnore
    public List<Product> getProducts() {
        return products;
    }
    @JsonSetter("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
