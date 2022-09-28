package models.Party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OtherNameOrganization extends PanacheEntity {
    public String name;
    public String type;
    public String tradingName;
    public Date validFrom;
    public Date validUntil;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "organization")
    public Organization organization;
}
