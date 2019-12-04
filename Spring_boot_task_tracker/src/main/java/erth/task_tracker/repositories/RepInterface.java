package erth.task_tracker.repositories;

import erth.task_tracker.entities.Task;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public interface RepInterface {
    void addTask(Task task) throws SQLException;
    void delTask(long idForDel) throws SQLException;
    void delTask(String nameForDel) throws SQLException;
    void clearTaskList() throws SQLException;
    List<Task> getAllTasks() throws SQLException;
    List<Task> findTaskById(long idForSearch) throws SQLException;
    List<Task> findTaskByName(String nameForSearch) throws SQLException;
    List<Task> findTaskByFilter(Long id,String name,String owner,String executer,String status) throws SQLException;
}
