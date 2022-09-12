package models;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class TaxExcemtionCertificate extends PanacheEntity {
    public Date validFrom;
    public Date validUntil;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    public AttachmentRefOrValue attachment;
    @OneToMany(mappedBy = "taxExcemtionCertificate", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<TaxDefinition> taxDefinitions;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "individual")
    public Individual individual;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "organization")
    public Organization organization;
}
