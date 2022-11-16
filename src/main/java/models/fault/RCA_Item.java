package models.fault;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;

@Entity
public class RCA_Item extends PanacheEntityBase{
    @Id 
    public int Item_id; 
    public String name;
    public String description;
    public String catogary;
    public int level;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "ItemClass_id")
    public RCA_Class ItemClass_id;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "related_process_id")
    public Process related_process_id;

    public RCA_Item() {
    }

    public RCA_Item(int Item_id, String name, String description, String catogary, int level, RCA_Class ItemClass_id, Process related_process_id) {

        this.Item_id = Item_id;
        this.name = name;
        this.description = description;
        this.catogary = catogary;
        this.level = level;
        this.ItemClass_id = ItemClass_id;
        this.related_process_id = related_process_id;
    }

}