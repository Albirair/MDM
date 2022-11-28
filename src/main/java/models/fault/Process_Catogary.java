package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import models.ModelBaseWithoutId;


@Entity
public class Process_Catogary extends ModelBaseWithoutId{
    @Id
    public int ProcessCatogary_id;
    public String name;
    public String description;
   
    // Primary Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "Related_ProcessCatogary")
    public Process Related_ProcessCatogary;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "Framework")
    public Framework ProcessCatogary_framework;   //eTOM

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_Catogaryapp")
    public Application related_CatogaryApp;

    public Process_Catogary() {
    }

    public Process_Catogary(int ProcessCatogary_id, String name, String description, Framework ProcessCatogary_framework, Application related_CatogaryApp ) {

        this.ProcessCatogary_id = ProcessCatogary_id;
        this.name = name;
        this.description = description;
        this.related_CatogaryApp = related_CatogaryApp;
        this.ProcessCatogary_framework = ProcessCatogary_framework;
        
    }

    @Override
    public long getId() {
        return ProcessCatogary_id;
    }

}
