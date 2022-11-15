package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import java.util.Set;




@Entity
public class Process extends PanacheEntityBase{
    @Id
    public int process_id;
    public String name;
    public String description;
    // Primary Key:
    @OneToOne(mappedBy = "related_process_id")
    public RCA_Item related_process_id;
    // Forign Key:
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "related_Process_app")
    // public Application related_Process_app;
    public Set<Application> related_Process_app;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "Related_ProcessCatogary")
    public Process_Catogary Related_ProcessCatogary;

    @ManyToOne
    @JsonBackReference(value = "Related_ProcessActivity")
    public Process_Activity Related_ProcessActivity;
    


    

    public Process() {
    }

    public Process(int process_id, String name, String description, Process_Activity Related_ProcessActivity ) {

        this.process_id = process_id;
        this.name = name;
        this.description = description;
        this.Related_ProcessActivity = Related_ProcessActivity;
        // this.related_Process_app = related_Process_app;
        // this.Related_ProcessCatogary = Related_ProcessCatogary;
        
    }

}