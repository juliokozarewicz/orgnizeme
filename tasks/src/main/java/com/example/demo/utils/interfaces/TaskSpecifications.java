package com.example.demo.utils.interfaces;

import com.example.demo.a_entity.TaskEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecifications {

    public static Specification<TaskEntity> filterTasks(

        String taskName

    ) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (taskName != null) {
                predicates.add(criteriaBuilder.equal(root.get("taskName"), taskName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}