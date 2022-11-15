package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import java.util.Set;



@Entity
public class Application extends PanacheEntityBase{
    @Id 
    public int application_id; 
    public String name;
    public String description;

    // Primary Key:
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
        name = "related_Process_app",
        joinColumns = {@JoinColumn(name = "application_id")},
        inverseJoinColumns = {@JoinColumn(name = "process_id")}) 
    public Set <Process> related_Process_app;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_CatogaryApp")
    public Application related_CatogaryApp;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_ActivityApp")
    public Application related_ActivityApp;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_TaskApp")
    public Application related_TaskApp;
    

    public Application() {
    }

    public Application (int application_id, String name, String description) {

        this.application_id = application_id;
        this.name = name;
        this.description = description;
        
    }
    
}
