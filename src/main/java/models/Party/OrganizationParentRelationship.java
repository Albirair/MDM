package models.party;

import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OrganizationParentRelationship extends PanacheEntity {
    public String relationshipType;
    @OneToOne
    @JoinColumn
    private Organization organizationRelationship;
    @OneToOne(mappedBy = "organizationParentRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrganizationRef organization;
}
