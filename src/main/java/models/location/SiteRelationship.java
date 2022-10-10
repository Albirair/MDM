package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class SiteRelationship extends PanacheEntity {

    public String href;
    public String type;
    public String role;
    public String subUnitNumber;

    public SiteRelationship() {
    }

    public SiteRelationship( String href, String type, String role, String subUnitNumber) {

        this.href = href;
        this.type = type;
        this.role = role;
        this.subUnitNumber = subUnitNumber;

    }

}
