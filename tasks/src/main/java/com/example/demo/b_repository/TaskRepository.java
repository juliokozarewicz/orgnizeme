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

    // find task by name
    List<TaskEntity> findByTaskName(String taskName);

    // find task by id
    List<TaskEntity> findById(String id);

    // delete task by id
    @Transactional
    void deleteById(String id);

}
