package erth.task_tracker.entities;

import com.geekbrains.gwt.common.TaskDto;
import erth.task_tracker.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tasklist")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "executer")
    private String executer;

    @Column(name = "summary")
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Task(String name, String owner, String executer, String summary, Status status) {
        this.name = name;
        this.owner = owner;
        this.executer = executer;
        this.summary = summary;
        this.status = status;
    }

    public Task(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.name = taskDto.getName();
        this.owner = taskDto.getOwner();
        this.executer = taskDto.getExecuter();
        this.summary = taskDto.getSummary();
        this.status = Status.OPEN;
    }

    @Override
    public String toString() {
        return "Задача: id=" + id + ", название: " + name + ", автор: " + owner + ", исполнитель: " + executer + ", описание: " + summary + ", статус: " + status.getRus();
    }
}
