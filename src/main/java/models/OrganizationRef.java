package models;
import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class OrganizationRef extends PanacheEntity {
    public String href;
    public String name;
    @OneToOne
    @JoinColumn
    private OrganizationChildRelationship organizationChildRelationship;
    @OneToOne
    @JoinColumn
    private OrganizationParentRelationship organizationParentRelationship;
}
