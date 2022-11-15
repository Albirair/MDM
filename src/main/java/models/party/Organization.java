package models.party;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.ModelBase;

@Entity
public class Organization extends ModelBase {
    @Column(updatable = false)
    public String href;
    public boolean isHeadOffice;
    public boolean isLegalEntity;
    public String name;
    public String nameType;
    public String type;
    @Column(nullable = false)
    public String tradingName;
    public Date existsSince;
    public Date existsUntil;
    public OrganizationStateType status;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<PartyCreditProfile> creditRating;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<ExternalReference> externalReference;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<OtherNameOrganization> otherName;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<Characteristic> partyCharacteristic;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<RelatedParty> relatedParty;
    @OneToMany(mappedBy = "organizationRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<OrganizationChildRelationship> organizationChildRelationship;
    @OneToOne(mappedBy = "organizationRelationship", cascade = CascadeType.ALL, orphanRemoval = true)
    public OrganizationParentRelationship organizationParentRelationship;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<ContactMedium> contactMedium;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<OrganizationIdentification> organizationIdentification;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "organization")
    public Set<TaxExcemtionCertificate> taxExcemtionCertificate;

    @Override
    protected void fixForeignKey(PanacheEntity element) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        if (element instanceof OrganizationChildRelationship)
            element.getClass().getMethod("setOrganizationRelationship", Organization.class)
                    .invoke(element, this);
        else
            super.fixForeignKey(element);
    }
}
