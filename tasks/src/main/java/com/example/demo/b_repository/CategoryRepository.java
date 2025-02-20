package com.example.demo.b_repository;

import com.example.demo.a_entity.CategoryEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    List<CategoryEntity> findAll();
    List<CategoryEntity> findByCategoryName(String categoryName);


}
