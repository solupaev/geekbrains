package erth.task_tracker.services;

import erth.task_tracker.entities.Task;
import erth.task_tracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delTask(long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> getTaskByFilter(Specification<Task> spec) {
        return taskRepository.findAll(spec);
    }

}
