package models;

import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class MediumCharachteristic extends PanacheEntity {
    public String city;
    public String contactType;
    public String country;
    public String emailAddress;
    public String faxNumber;
    public String phoneNumber;
    public String postCode;
    public String socialNetworkId;
    public String stateOrProvince;
    public String street1;
    public String street2;
    @OneToOne
    @JoinColumn
    private ContactMedium contactMedium;
}
