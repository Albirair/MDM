package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
// import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import models.ModelBase;
import javax.persistence.Entity;

@Entity
public class RCA extends ModelBase{
    /* @Id
    public int rca_id; */
    public String name;
    public String description;
    public int related_process_id;
    public String catogary;
    public int level;
    public int parent_id;
    // Forign Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "rca")
    public Problem problem;

    public RCA() {
    }

    public RCA(int rca_id, String name, String description, int related_process_id, String catogary,int level, int parent_id ) {

        // this.rca_id = rca_id;
        this.name = name;
        this.description = description;
        this.related_process_id = related_process_id;
        this.catogary = catogary;
        this.level = level;
        this.parent_id = parent_id;
    }

}