package models.location;
// import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.ModelBase;
import javax.persistence.*;

@Entity
public class GeographicAddress extends ModelBase {

    public String href;
    public String streetNr;
    public String streetNrSuffix;
    public String streetNrLast;
    public String streetName;
    public String streetType;
    public String streetSuffix;
    public String postcode;
    // primary key:
    @OneToOne(mappedBy = "GeoAdd_office")
    public Office GeoAdd_office;
    @OneToOne(mappedBy = "GeographicAddress")
    public GeographicSubAddress GeographicSubAddress;
    // Forign key:
    @OneToOne
    @JoinColumn(name = "GeAdd_city")
    public City GeAdd_city;
    @OneToOne
    @JoinColumn(name = "GeographicLocation")
    public GeographicLocation GeographicLocation;
    @OneToOne
    @JoinColumn(name = "GeoAdd_Country")
    public Country GeoAdd_Country;
    @OneToOne
    @JoinColumn(name = "GeoAdd_State")
    public State GeoAdd_State;
    @OneToOne
    @JoinColumn(name = "GeoAdd_Continent")
    public Continent GeoAdd_Continent;
    @OneToOne
    @JoinColumn(name = "GeoAdd_Locality")
    public Locality GeoAdd_Locality;
    @OneToOne
    @JoinColumn(name = "GeoAdd_BlockHay")
    public Block_Hay GeoAdd_BlockHay;
}