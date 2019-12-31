package erth.task_tracker.controllers;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.entities.Task;
import erth.task_tracker.enums.Status;
import erth.task_tracker.repositories.specifications.TaskSpecifications;
import erth.task_tracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public List<TaskDto> getAllTasks(@RequestParam(name = "nameFilter", required = false) String nameFilter
                                    ,@RequestParam(name = "ownerFilter", required = false) String ownerFilter
                                    ,@RequestParam(name = "executerFilter", required = false) String executerFilter
                                    ,@RequestParam(name = "statusFilter", required = false) String statusFilter
                                    ) {

        Specification<Task> spec = Specification.where(null);

        if (nameFilter != null && !nameFilter.equals("null")){
            spec = spec.and(TaskSpecifications.nameFilter(nameFilter));
        }
        if (ownerFilter != null && !ownerFilter.equals("null")){
            spec = spec.and(TaskSpecifications.ownerFilter(ownerFilter));
        }
        if (executerFilter != null && !executerFilter.equals("null")){
            spec = spec.and(TaskSpecifications.executerFilter(executerFilter));
        }
        if (statusFilter != null && statusFilter != "" && !statusFilter.equals("null")){
            spec = spec.and(TaskSpecifications.statusFilter(Status.valueOf(statusFilter)));
        }

        List<TaskDto> tasks = taskService.getAll(spec);
        return tasks;
    }

    @GetMapping("/tasks/remove/{id}")
    public ResponseEntity<String> removeTasks(@PathVariable Long id) {
        taskService.delTask(id);
        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public TaskDto createNewTask(@RequestBody TaskDto taskDto) {
        System.out.println("Пришло на создание: " + taskDto.getName());
        return taskService.save(taskDto);
    }

}