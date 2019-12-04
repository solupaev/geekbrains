package erth.task_tracker.repositories;

import erth.task_tracker.entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepository implements RepInterface {
    private Session session;
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public TaskRepository() {
    }

    /*
    @Autowired
    public void setFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     */

    //добавляем запись в таблицу tasklist
    @Override
    public void addTask(Task task) {
        session = entityManager.unwrap(Session.class);
        session.save(task);
    }

    //очищаем таблицу tasklist
    @Override
    public void clearTaskList() {
        session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("DELETE FROM Task");
        query.executeUpdate();
    }

    //удаляем запись из таблицы tasklist по id
    @Override
    public void delTask(long idForDel) {
        session = entityManager.unwrap(Session.class);
        session.delete(session.get(Task.class, idForDel));
    }

    //удаляем запись из таблицы tasklist по имени
    @Override
    public void delTask(String nameForDel) {
        long idForDel;
        session = entityManager.unwrap(Session.class);
        idForDel = session.createQuery("SELECT t.id FROM Task t WHERE t.name = :name", Long.class)
                .setParameter("name", nameForDel)
                .getSingleResult();
        session.delete(session.get(Task.class, idForDel));
    }

    //ищем запись из таблицы tasklist по имени
    @Override
    public List<Task> findTaskByName(String nameForSearch) {
        List<Task> resultList;
        session = entityManager.unwrap(Session.class);
        resultList = session.createQuery("SELECT t FROM Task t WHERE t.name = :name", Task.class)
                .setParameter("name", nameForSearch)
                .getResultList();
        return resultList;
    }

    //ищем запись из таблицы tasklist по имени
    @Override
    public List<Task> findTaskById(long idForSearch) {
        List<Task> resultList;
        session = entityManager.unwrap(Session.class);
        try {
            resultList = session.createQuery("SELECT t FROM Task t WHERE t.id = :id", Task.class)
                    .setParameter("id", idForSearch)
                    .getResultList();
            return resultList;
        } catch(NoResultException e) {
            return null;
        }
    }

    //ищем запись из таблицы tasklist по фильтру
    @Override
    public List<Task> findTaskByFilter(Long id,String name,String owner,String executer,String status) {
        List<Task> resultList;
        session = entityManager.unwrap(Session.class);
        try {
            resultList = session.createQuery("SELECT t FROM Task t WHERE 1 = 1" +
                            "and (t.id = :id or :id is null)" +
                            "and (t.name like :name or :name is null)" +
                            "and (t.owner like :owner or :owner is null)" +
                            "and (t.executer like :executer or :executer is null)" +
                            "and (t.status like :status or :status is null)"
                    , Task.class)
                    .setParameter("id", id)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("owner", "%" + owner + "%")
                    .setParameter("executer", "%" + executer + "%")
                    .setParameter("status", "%" + status + "%")
                    .getResultList();
            return resultList;
        } catch(NoResultException e) {
            return null;
        }
    }

    //печать всех данных из таблицы
    @Override
    public List<Task> getAllTasks() {
        session = entityManager.unwrap(Session.class);
        List<Task> resultList;
        resultList = session.createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();
        return resultList;
    }
}
