package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import java.util.Set;
import models.ModelBaseWithoutId;


@Entity
public class Process_Task extends ModelBaseWithoutId{
    @Id
    public int processTask_id;
    public String name;
    public String description;
    // Forign Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_TaskApp")
    public Application related_TaskApp;

    @ManyToMany(mappedBy = "Related_ProcessTask", cascade = CascadeType.ALL)
    // @JoinColumn
	@JsonManagedReference(value = "Related_ProcessTask")
    public Set <Process_Activity> Related_ProcessTask;

    public Process_Task() {
    }

    public Process_Task(int processTask_id, String name, String description, Application related_TaskApp, Process_Activity Related_ProcessTask ) {

        this.processTask_id = processTask_id;
        this.name = name;
        this.description = description;
        this.related_TaskApp = related_TaskApp;
        // this.Related_ProcessTask = Related_ProcessTask;
    }

    @Override
    public long getId() {
        return processTask_id;
    }
}