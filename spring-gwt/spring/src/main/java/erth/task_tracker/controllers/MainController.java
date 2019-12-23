package erth.task_tracker.controllers;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.entities.Task;
import erth.task_tracker.enums.Status;
import erth.task_tracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin
public class MainController {
    private TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = taskService.getAll();
        return tasks;
    }

    @GetMapping("/tasks/remove/{id}")
    public ResponseEntity<String> removeTasks(@PathVariable Long id) {
        taskService.delTask(id);
        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public TaskDto createNewTask(@ModelAttribute TaskDto taskDto) {
        return taskService.save(taskDto);
    }

}