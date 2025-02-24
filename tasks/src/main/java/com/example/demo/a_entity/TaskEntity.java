package com.example.demo.a_entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class TaskEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "task", nullable = false, length = 255)
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
    private Date dueDate;

    // constructor
    public TaskEntity(
        String id,
        Timestamp createdAt,
        Timestamp updatedAt
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // methods
    public static TaskEntity createUpdateTask(
        String id,
        Timestamp createdAt,
        Timestamp updatedAt
    ) {

        return new TaskEntity(
            id,
            createdAt,
            updatedAt
        );

    }
}
