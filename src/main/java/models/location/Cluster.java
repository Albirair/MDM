package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;


@Entity
public class Cluster extends PanacheEntity{

    public String name;
    // Forign Key:
    @ManyToOne
    @JoinColumn(name = "officeId_cluster")
    @JsonBackReference(value = "officeId_cluster")
    public Office officeId_cluster;

    public Cluster() {
    }

    public Cluster( String name, Office officeId_cluster) {

        this.name = name;
        this.officeId_cluster = officeId_cluster;
    }
}