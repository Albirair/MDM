package models.fault;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import models.ModelBase;

@Entity
public class Problem extends PanacheEntityBase {

    @Id
    public int problem_id; 
    public String name;
    public String description;
    public String comment;
    public int related_item_id;
    // Primary Key:
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "related_rca_family")
    public Set<RCA_Family> related_rca_family;

    public Problem() {
    }

    public Problem(int problem_id, String name, String description, String comment, int related_item_id) {

        // this.problem_id = problem_id;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.related_item_id = related_item_id;
    }
}