package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import java.util.Set;
import models.ModelBaseWithoutId;



@Entity
public class Process_Activity extends ModelBaseWithoutId{
    @Id
    public int processActivity_id;
    public String name;
    public String description;
    // Primary Key:
    // @OneToOne(mappedBy = "Related_ProcessTask")
    // public Process_Task Related_ProcessTask;
    
    // Forign Key:
    @OneToMany(mappedBy = "Related_ProcessCatogary", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "Related_ProcessCatogary")
    public Set <Process> Related_ProcessCatogary;

    @OneToMany(mappedBy = "Related_ProcessActivity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "Related_ProcessActivity")
    public Set<Process> Related_ProcessActivity;

    @OneToMany
    @JsonBackReference(value = "Related_ProcessTask")
    public Set <Process_Task> Related_ProcessTask;

    public Process_Activity() {
    }

    public Process_Activity(int processActivity_id, String name, String description, Application related_ActivityApp, Process_Task Related_ProcessTask ) {

        this.processActivity_id = processActivity_id;
        this.name = name;
        this.description = description;
        // this.related_ActivityApp = related_ActivityApp;
        // this.Related_ProcessTask =  Related_ProcessTask;
    }

    @Override
    public long getId() {
        return processActivity_id;
    }

}
