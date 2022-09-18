package models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OrganizationChildRelationship extends PanacheEntity {
    public String relationshipType;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "organization")
    public Organization organizationRelationship;
    @OneToOne(mappedBy = "organizationChildRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    public OrganizationRef organization;
}
