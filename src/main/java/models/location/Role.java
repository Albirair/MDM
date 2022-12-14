package models.location;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Role extends PanacheEntity{

    public String name;
    // ADD description (optional!)
    
    // Primary Key:
    @OneToMany
    @JsonManagedReference(value = "Role_SubRole")
    public Set <SubRole> Role_SubRole;

    @OneToOne(mappedBy = "Role_SiteRole")
    @JoinColumn
    public Site_Role Role_SiteRole;

    public Role() {
    }

    public Role( String name) {

        this.name = name;
    }

}
