package models.Party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class PaymentMethodRef extends PanacheEntity {
    public String href;
    public String name;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "partyRole")
    public PartyRole partyRole;
}
