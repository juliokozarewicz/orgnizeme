package com.example.demo.b_repository;

import com.example.demo.a_entity.TaskEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends

    JpaRepository<TaskEntity, UUID>,
    JpaSpecificationExecutor<TaskEntity>

{
    // getl all results
    List<TaskEntity> findAll();

    // find task by name
    Optional<TaskEntity> findByTaskNameAndDueDate(String taskName, LocalDate dueDate);

    // find task by id
    Optional<TaskEntity> findById(String id);

    // delete task by id
    @Transactional
    void deleteById(String id);

}
