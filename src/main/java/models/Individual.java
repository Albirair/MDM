package models;

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

    // public Individual() {
    // super();
    // }

    // public Individual(JsonObject individual) {
    // super();

    // }

    // public void patch(Individual i) {
    // aristoctraticTitle = i.aristoctraticTitle;
    // birthDate = i.birthDate;
    // countryOfBirth = i.countryOfBirth;
    // deathDate = i.deathDate;
    // familyName = i.familyName;
    // familyNamePrefix = i.familyNamePrefix;
    // formattedName = i.formattedName;
    // fullName = i.fullName;
    // gender = i.gender;
    // generation = i.generation;
    // givenName = i.givenName;
    // legalName = i.legalName;
    // location = i.location;
    // maritalStatus = i.maritalStatus;
    // middleName = i.middleName;
    // nationality = i.nationality;
    // placeOfBirth = i.placeOfBirth;
    // preferredGivenName = i.preferredGivenName;
    // title = i.title;
    // status = i.status;
    // }

    @Override
    public String toString() {
        return "individual " + id + " is " + givenName + " " + familyName;
    }
}
