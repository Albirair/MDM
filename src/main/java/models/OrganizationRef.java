package models;
import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OrganizationRef extends PanacheEntity {
    public String href;
    public String name;
    @OneToOne(mappedBy = "organization")
    private OrganizationChildRelationship organizationChildRelationship;
    @OneToOne(mappedBy = "organization")
    private OrganizationParentRelationship organizationParentRelationship;
}
