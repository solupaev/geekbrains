package erth.task_tracker.repositories;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Query("Select new com.geekbrains.gwt.common.TaskDto(t.id, t.name, t.owner, t.executer, t.summary) from Task t")
    List<TaskDto> findAllDtos();
}
