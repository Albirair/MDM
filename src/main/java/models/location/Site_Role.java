package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class Site_Role extends PanacheEntity{

    // Forign Key:
    @OneToOne
    @JoinColumn(name = "GeoSite_SiteRole")
    public GeographicSite GeoSite_SiteRole;
    @OneToOne
    @JoinColumn(name = "Role_SiteRole")
    public Role Role_SiteRole;

    public Site_Role() {
    }

    public Site_Role( GeographicSite GeoSite_SiteRole, Role Role_SiteRole) {

        this.GeoSite_SiteRole = GeoSite_SiteRole;
        this.Role_SiteRole = Role_SiteRole;
    }
}
