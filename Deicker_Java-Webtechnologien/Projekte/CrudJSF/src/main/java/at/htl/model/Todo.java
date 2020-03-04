package at.htl.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Todo implements Serializable, Comparable<Todo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String title;

    private Date dueDate;

    @NotNull
    private Date plannedDate;
    private boolean done;

    public Todo(String title, Date dueDate, Date plannedDate) {
        this(title, plannedDate);
        this.dueDate = dueDate;
        this.done = true;
    }

    public Todo(String title, Date plannedDate ){
        this();
        this.title = title;
        this.plannedDate = plannedDate;
    }
    public Todo() {
        dueDate = null;
        plannedDate = new Date();
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public Date getPlannedDate() {
        return plannedDate;
    }
    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    @Override
    public int compareTo(Todo o) {
        if (o.dueDate == null){
            return 1;
        }
        else if (this.dueDate == null){
            return -1;
        }
        return o.dueDate.compareTo(this.dueDate);
    }
}
