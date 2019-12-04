package erth.task_tracker.services;

import erth.task_tracker.entities.Task;
import erth.task_tracker.repositories.RepInterface;
import erth.task_tracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TaskService {
    private RepInterface taskRepository ;

    @Autowired
    public void setTaskRepository(RepInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskService() {
    }

    public void addTask(Task task) {
        try {
            taskRepository.addTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delTask(long id) {
        try {
            taskRepository.delTask(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delTask(String name) {
        try {
            taskRepository.delTask(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTaskList () {
        try {
            taskRepository.clearTaskList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getTaskList() {
        try {
            return taskRepository.getAllTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTaskById(long id) {
        try {
            return taskRepository.findTaskById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTaskByName(String name) {
        try {
            return taskRepository.findTaskByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTaskByFilter(Long id,String name,String owner,String executer,String status) {
        try {
            return taskRepository.findTaskByFilter(id,name,owner,executer,status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
