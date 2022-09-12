package models;

import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OrganizationParentRelationship extends PanacheEntity {
    public String relationshipType;
    @OneToOne(mappedBy = "organizationParentRelationship")
    private Organization organizationRelationship;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private OrganizationRef organization;
}
