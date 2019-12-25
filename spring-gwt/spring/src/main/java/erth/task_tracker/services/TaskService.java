package erth.task_tracker.services;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.entities.Task;
import erth.task_tracker.enums.Status;
import erth.task_tracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TaskDto save(TaskDto taskDto) {
        if (taskDto.getId() == null) {
            System.out.println("Создаем задачу");
            Task item = new Task(taskDto);
            item.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println("Автор: " + item.getOwner());
            taskRepository.save(item);
            return new TaskDto(item.getId(), item.getName(), item.getOwner(), item.getExecuter(), item.getSummary(), item.getStatus().toString());
        } else {
            System.out.println("Редактируем задачу");
            Task item = taskRepository.getOne(taskDto.getId());
            item.setName(taskDto.getName());
            item.setOwner(taskDto.getOwner());
            item.setExecuter(taskDto.getExecuter());
            item.setSummary(taskDto.getSummary());
            item.setStatus(Status.valueOf(taskDto.getStatus()));
            taskRepository.save(item);
            return taskDto;
        }
    }

    public void delTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public List<TaskDto> getAll(Specification<Task> spec) {
        List<Task> tasks = (List<Task>) taskRepository.findAll(spec);

        return tasks.stream().map(task -> new TaskDto(task.getId(),task.getName(),task.getOwner(),task.getExecuter(),task.getSummary(),task.getStatus().toString())).collect(Collectors.toList());
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    public Page<Task> findAll(Specification<Task> spec, Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

}
