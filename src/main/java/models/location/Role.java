package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class Role extends PanacheEntity{

    public String name;
    // Primary Key:
    @OneToOne(mappedBy = "Role_SubRole")
    public SubRole Role_SubRole;
    @OneToOne(mappedBy = "Role_SiteRole")
    public Site_Role Role_SiteRole;

    public Role() {
    }

    public Role( String name) {

        this.name = name;
    }

}
