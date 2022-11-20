package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import java.util.Set;
import models.ModelBaseWithoutId;




@Entity
public class Application extends ModelBaseWithoutId{
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

    public Application (int application_id, String name, String description, Application related_CatogaryApp, Application related_ActivityApp, Application related_TaskApp) {

        this.application_id = application_id;
        this.name = name;
        this.description = description;
        this.related_CatogaryApp = related_CatogaryApp;
        this.related_ActivityApp = related_ActivityApp;
        this.related_TaskApp = related_TaskApp;
        
    }
    @Override
    public long getId() {
        return application_id;
    }
   
}
