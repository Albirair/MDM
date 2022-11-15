package models.fault;

import javax.persistence.*;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;

@Entity
public class RCA_Class extends PanacheEntityBase{
    @Id 
    public int class_id; 
    public String name;
    public String description;
    public String catogary;
    public int level;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "ClassFamily_id")
    public RCA_Family ClassFamily_id;
    // Primary key:
    @OneToOne
    @JoinColumn(name = "ItemClass_id")
    public RCA_Item ItemClass_id;

    public RCA_Class() {
    }

    public RCA_Class(int class_id, String name, String description,  String catogary, int level, RCA_Family ClassFamily_id) {

        this.class_id = class_id;
        this.name = name;
        this.description = description;
        this.catogary = catogary;
        this.level = level;
        this.ClassFamily_id = ClassFamily_id;
    }

}