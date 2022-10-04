package models.party;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Individual extends PanacheEntity {
    @Column(updatable = false)
    private String href;
    public String aristoctraticTitle;
    public Date birthDate;
    public String countryOfBirth;
    public Date deathDate;
    @Column(nullable = false)
    public String familyName;
    public String familyNamePrefix;
    public String formattedName;
    public String fullName;
    public String gender;
    public String generation;
    @Column(nullable = false)
    public String givenName;
    public String legalName;
    public String location;
    public String maritalStatus;
    public String middleName;
    public String nationality;
    public String placeOfBirth;
    public String preferredGivenName;
    public String title;
    public IndividualStateType status;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<PartyCreditProfile> creditRating;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<Disability> disability;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<ExternalReference> externalReference;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<OtherNameIndividual> otherName;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<Characteristic> partyCharacteristic;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<LanguageAbility> languageAbility;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<IndividualIdentification> individualIdentification;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<TaxExcemtionCertificate> taxExcemtionCertificate;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<ContactMedium> contactMedium;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<RelatedParty> relatedParty;
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "individual")
    public Set<Skill> skill;
}
