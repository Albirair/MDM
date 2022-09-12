package models;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class IndividualIdentification extends PanacheEntity {
    public String identificationId;
    public String identificationType;
    public String issuingAuthority;
    public Date validFrom;
    public Date validUntil;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AttachmentRefOrValue attachment;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "individual")
    public Individual individual;
}
