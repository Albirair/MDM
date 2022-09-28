package models.Party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class OrganizationIdentification extends PanacheEntityBase {
    @Id
    private String identificationId;
    public String identificationType;
    public String issuingAuthority;
    public Date validFrom;
    public Date validUntil;
    @OneToOne(mappedBy = "organizationIdentification", cascade = CascadeType.ALL, orphanRemoval = true)
    private AttachmentRefOrValue attachment;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "organization")
    public Organization organization;
}
