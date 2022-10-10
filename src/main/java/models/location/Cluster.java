package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class Cluster extends PanacheEntity{

    public String name;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "officeId_cluster")
    public Office officeId_cluster;
    public Cluster() {
    }

    public Cluster( String name, Office officeId_cluster) {

        this.name = name;
        this.officeId_cluster = officeId_cluster;
    }
}