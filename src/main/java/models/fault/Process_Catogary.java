package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import java.util.Set;
import models.ModelBaseWithoutId;



@Entity
public class Process_Catogary extends ModelBaseWithoutId{
    @Id
    public int ProcessCatogary_id;
    public String name;
    public String description;
    public String Process_framework; //eTOM , PCF
    // Primary Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "Related_ProcessCatogary")
    public Process Related_ProcessCatogary;
    // Forign Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_Catogaryapp")
    public Application related_CatogaryApp;

    public Process_Catogary() {
    }

    public Process_Catogary(int ProcessCatogary_id, String name, String description, Application related_CatogaryApp ) {

        this.ProcessCatogary_id = ProcessCatogary_id;
        this.name = name;
        this.description = description;
        this.related_CatogaryApp = related_CatogaryApp;
        // this.Related_ProcessCatogary = Related_ProcessCatogary;
        
    }

    @Override
    public long getId() {
        return ProcessCatogary_id;
    }

}
