package erth.task_tracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@NoArgsConstructor
@Data
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

    public Task(String name, String owner, String executer, String summary, String status) {
        this.name = name;
        this.owner = owner;
        this.executer = executer;
        this.summary = summary;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача: id=" + id + ", название: " + name + ", автор: " + owner + ", исполнитель: " + executer + ", описание: " + summary + ", статус: " + status;
    }
}
