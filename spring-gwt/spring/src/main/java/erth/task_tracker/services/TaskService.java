package erth.task_tracker.services;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.entities.Task;
import erth.task_tracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository ;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void delTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public List<TaskDto> getAll() {
        return taskRepository.findAllDtos();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    public Page<Task> findAll(Specification<Task> spec, Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

}
