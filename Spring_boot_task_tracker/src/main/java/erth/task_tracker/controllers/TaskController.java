package erth.task_tracker.controllers;


import erth.task_tracker.entities.Task;
import erth.task_tracker.repositories.specifications.TaskSpecifications;
import erth.task_tracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController() {
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping({"/"})
    public String showHomePage(Model model) {
        List<Task> tasks = taskService.getTaskList();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping({"/show_task"})
    public String showTask(@RequestParam("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task_form";
    }

    @GetMapping({"/del_task"})
    public String delTask(@RequestParam("id") Long id) {
        taskService.delTask(id);
        return "redirect:/";
    }

    @GetMapping({"/add"})
    public String addTaskForm (Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "add_task_form";
    }

    @PostMapping({"/add"})
    public String addTaskProcess (@ModelAttribute("task") Task task) {
        taskService.addTask(task);
        return "redirect:/";
    }

    @PostMapping({"/filter"})
    public String filterTask(Model model,
                             @RequestParam (required = false) Long id,
                             @RequestParam (required = false) String name,
                             @RequestParam (required = false) String owner,
                             @RequestParam (required = false) String executer,
                             @RequestParam (required = false) String status
    ) {
        Specification<Task> spec = Specification.where(null);
        if (id != null) {
            spec = spec.and(TaskSpecifications.idFilter(id));
        }
        if (name != null) {
            spec = spec.and(TaskSpecifications.nameFilter(name));
        }
        if (owner != null) {
            spec = spec.and(TaskSpecifications.ownerFilter(owner));
        }
        if (executer != null) {
            spec = spec.and(TaskSpecifications.executerFilter(executer));
        }
        if (status != null) {
            spec = spec.and(TaskSpecifications.statusFilter(status));
        }
        List<Task> tasks = taskService.getTaskByFilter(spec);
        model.addAttribute("tasks", tasks);
        return "index";
    }

}
