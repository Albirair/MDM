package models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class TaxDefinition extends PanacheEntity {
    public String name;
    public String taxType;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private TaxExcemtionCertificate taxExcemtionCertificate;
}
