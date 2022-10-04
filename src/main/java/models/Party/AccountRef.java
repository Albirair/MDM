package models.party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class AccountRef extends PanacheEntity {
    public String description;
    public String href;
    public String name;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "partyRole")
    public PartyRole partyRole;
}
