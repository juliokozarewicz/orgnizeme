package com.example.demo.a_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor @ToString
public class CategoryEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    // constructor
    public CategoryEntity(
        String id,
        Timestamp createdAt,
        Timestamp updatedAt,
        String categoryName
        ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryName = categoryName;
    }

    // methods
    public static CategoryEntity createUpdateCategory(
        String id,
        Timestamp createdAt,
        Timestamp updatedAt,
        String categoryName
    ) {

        return new CategoryEntity(
            id,
            createdAt,
            updatedAt,
            categoryName
        );

    }
}
