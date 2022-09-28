package models.Party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Characteristic extends PanacheEntity {
    public String name;
    public String valueType;
    public String value;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "individual")
    public Individual individual;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "organization")
    public Organization organization;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "partyRole")
    public PartyRole partyRole;
}
