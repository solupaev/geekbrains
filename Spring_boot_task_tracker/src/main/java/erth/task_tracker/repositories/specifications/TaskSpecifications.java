package erth.task_tracker.repositories.specifications;

import erth.task_tracker.entities.Task;
import erth.task_tracker.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> idFilter (Long id) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Task> nameFilter (String name) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Task> ownerFilter (String owner) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("owner"), "%" + owner + "%");
    }

    public static Specification<Task> executerFilter (String executer) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("executer"), "%" + executer + "%");
    }

    public static Specification<Task> statusFilter (Status status) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
