package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import java.util.Set;
import models.ModelBaseWithoutId;



@Entity
public class Process extends ModelBaseWithoutId{
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

    @OneToMany
    @JsonBackReference(value = "Related_ProcessCatogary")
    public Set <Process_Catogary> Related_ProcessCatogary;

    @OneToMany
    @JsonBackReference(value = "Related_ProcessActivity")
    public Set <Process_Activity> Related_ProcessActivity;





    public Process() {
    }

    public Process(int process_id, String name, String description, Process_Activity Related_ProcessActivity, RCA_Item related_process_id, Process_Catogary Related_ProcessCatogary ) {

        this.process_id = process_id;
        this.name = name;
        this.description = description;
        // this.Related_ProcessActivity = Related_ProcessActivity;
        this.related_process_id = related_process_id;
        // this.Related_ProcessCatogary =  Related_ProcessCatogary;

    }

    @Override
    public long getId() {
        return process_id;
    }

}