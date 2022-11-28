package models.fault;

import javax.persistence.*;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import models.ModelBaseWithoutId;


@Entity
public class Framework extends ModelBaseWithoutId{
    @Id
    public int framework_id;
    public String name;
    public String description;
    // Primary Key:
    @OneToOne
    @JoinColumn(name = "Framework")
    public Process_Catogary ProcessCatogary_framework;


    public Framework() {
    }

    public Framework(int framework_id, String name, String description, Process_Catogary ProcessCatogary_framework ) {

        this.framework_id = framework_id;
        this.name = name;
        this.description = description;
        this.ProcessCatogary_framework = ProcessCatogary_framework;
        
    }

    @Override
    public long getId() {
        return framework_id;
    }

}
