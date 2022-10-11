package models.party;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import models.ModelBase;

@Entity
public class PartyRole extends ModelBase {
    @Column(updatable = false)
    public String href;
    @Column(nullable = false)
    public String name;
    public String status;
    public String statusReason;
    public Date validFrom;
    public Date validUntil;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    public RelatedParty engageParty;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<PartyCreditProfile> creditProfile;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<PaymentMethodRef> paymentMethod;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<AccountRef> account;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<AgreementRef> agreement;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<ContactMedium> contactMedium;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<RelatedParty> relatedParty;
    @OneToMany(mappedBy = "partyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "partyRole")
    public Set<Characteristic> characteristic;
}
