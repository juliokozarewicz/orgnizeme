package com.example.demo.a_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "taskName", nullable = false, length = 255)
    private String taskName;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "category", nullable = false, length = 255)
    private String category;

    @Column(name = "priority", nullable = false, length = 255)
    private String priority;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @Column(name = "due_date")
    private LocalDate dueDate;

}