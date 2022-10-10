package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class SubRole extends PanacheEntity{

    public String name;
    @OneToOne
    @JoinColumn(name = "Role_SubRole")
    public Role Role_SubRole;

    public SubRole() {
    }

    public SubRole( String name, Role Role_SubRole) {

        this.name = name;
        this.Role_SubRole = Role_SubRole;
    }
}