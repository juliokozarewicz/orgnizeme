package com.example.demo.b_repository;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.utils.interfaces.CategoryProjection;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    // get all categories
    List<TaskEntity> findAll();

    // return all categories filtered
    @Query(
        "SELECT c.id AS id, c.taskName AS taskName FROM TaskEntity c"
    )
    List<CategoryProjection> findTaskFiltered();

    // find category by name
    List<TaskEntity> findByTaskName(String taskName);

    // find category by name
    List<TaskEntity> findByI1d(String id);

    // delete category by name
    @Transactional
    void deleteById(String id);

}
