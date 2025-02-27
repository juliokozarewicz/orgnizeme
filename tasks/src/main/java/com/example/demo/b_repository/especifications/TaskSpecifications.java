package com.example.demo.b_repository.especifications;

import com.example.demo.a_entity.TaskEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecifications {

    public static Specification<TaskEntity> filterTasks(

        String taskName,
        String description,
        String category,
        String priority,
        String status,
        LocalDate initDate,
        LocalDate endDate

    ) {

        return (
            root,
            query,
            criteriaBuilder
        ) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (taskName != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("taskName")),
                        "%" + taskName.toLowerCase() + "%"
                    )
                );
            }

            if (description != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + description.toLowerCase() + "%"
                    )
                );
            }

            if (category != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("category")),
                        "%" + category.toLowerCase() + "%"
                    )
                );
            }

            if (priority != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("priority")),
                        "%" + priority.toLowerCase() + "%"
                    )
                );
            }

            if (status != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("status")),
                        "%" + status.toLowerCase() + "%"
                    )
                );
            }

            if (initDate != null) {
                predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                        root.get("dueDate"),initDate
                    )
                );
            }

            if (endDate != null) {
                predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                        root.get("dueDate"),
                        endDate
                    )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}