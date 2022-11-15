package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;

@Entity
public class RCA_Family extends PanacheEntityBase{
    @Id
    public int family_id; 
    public String name;
    public String description;
    public String catogary;
    // level is constant
    public int level; 
    // Forign Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_rca_family")
    public Problem problem;
    // Primary key:
    @OneToOne
    @JoinColumn(name = "ClassFamily_id")
    public RCA_Class ClassFamily_id;

    public RCA_Family() {
    }

    public RCA_Family(int family_id, String name, String description, String catogary,int level) {

        this.family_id = family_id;
        this.name = name;
        this.description = description;
        this.catogary = catogary;
        this.level = level;
    }

}