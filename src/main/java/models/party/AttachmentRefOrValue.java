package models.party;

import java.util.Date;
import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class AttachmentRefOrValue extends PanacheEntity {
    public String href;
    public String attachmentType;
    public String content;
    public String description;
    public boolean isRef;
    public String mimeType;
    public String name;
    public String url;
    public float size;
    public Date validFrom;
    public Date validUntil;
    @OneToOne
    @JoinColumn
    private TaxExcemtionCertificate taxExcemtionCertificate;
    @OneToOne
    @JoinColumn
    private IndividualIdentification individualIdentification;
    @OneToOne
    @JoinColumn
    private OrganizationIdentification organizationIdentification;
}
