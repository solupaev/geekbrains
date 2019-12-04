package erth.task_tracker.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "tasklist")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "executer")
    private String executer;

    @Column(name = "summary")
    private String summary;

    @Column(name = "status")
    private String status;

    public Task(long id, String name, String owner, String executer, String summary, String status) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.executer = executer;
        this.summary = summary;
        this.status = status;
    }

    public Task(String name, String owner, String executer, String summary, String status) {
        this.name = name;
        this.owner = owner;
        this.executer = executer;
        this.summary = summary;
        this.status = status;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getExecuter() {
        return executer;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() { return status; }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaskStatusOrder() {
        switch (getStatus()) {
            case "Planned": return 1;
            case "Open": return 2;
            case "InProgress": return 3;
            case "Closed": return 4;
            default: return 2147483647; //не описанные в сортировке статусы выводим в последнюю очередь
        }
    }

    public String prepareToUpload() {
        return id + ";" + name + ";" + owner + ";" + executer + ";" + summary + ";" + status + ";\n";
    }

    @Override
    public String toString() {
        return "Задача: id=" + id + ", название: " + name + ", автор: " + owner + ", исполнитель: " + executer + ", описание: " + summary + ", статус: " + status;
    }
}
