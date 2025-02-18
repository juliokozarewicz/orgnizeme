package com.example.demo.a_entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    // constructor
    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    // methods
    public static CategoryEntity createCategory(String categoryName) {
        return new CategoryEntity(categoryName);
    }
}
