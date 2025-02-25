package com.example.demo.b_repository;

import com.example.demo.a_entity.CategoryEntity;
import com.example.demo.utils.interfaces.CategoryProjection;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    // get all categories
    List<CategoryEntity> findAll();

    // return all categories filtered
    @Query(
        "SELECT c.id AS id, c.categoryName AS categoryName FROM CategoryEntity c"
    )
    List<CategoryProjection> findCategoryFiltered();

    // find category by name
    Optional<CategoryEntity> findByCategoryName(String categoryName);

    // find category by id
    Optional<CategoryEntity> findById(String id);

    // delete category by id
    @Transactional
    void deleteById(String id);

}
