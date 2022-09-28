package models.Party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Skill extends PanacheEntity {
    public String comment;
    public String evaluatedLevel;
    public String code;
    public String name;
    public Date validFrom;
    public Date validUntil;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "individual")
    public Individual individual;
}
