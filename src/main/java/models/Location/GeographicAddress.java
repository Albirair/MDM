package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class GeographicAddress extends PanacheEntity{
    
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

    // public GeographicAddress() {
    // }

    // public GeographicAddress( String href, String streetNr, String streetNrSuffix, String streetNrLast, String streetName, String streetType, String streetSuffix, String postcode,City GeAdd_city,GeographicSubAddress GeographicSubAddress, Continent GeoAdd_Continent, Country GeoAdd_Country, State GeoAdd_State, Locality GeoAdd_Locality, Block_Hay GeoAdd_BlockHay) {
        
    //     this.href = href;
    //     this.streetNr = streetNr;
    //     this.streetNrSuffix = streetNrSuffix;
    //     this.streetNrLast = streetNrLast;
    //     this.streetName = streetName;
    //     this.streetType = streetType;
    //     this.streetType = streetNrSuffix;
    //     this.streetType = streetType;
    //     this.postcode = postcode;
    //     this.GeAdd_city = GeAdd_city;
    //     this.GeographicSubAddress = GeographicSubAddress;
    //     this.GeoAdd_Continent = GeoAdd_Continent;
    //     this.GeoAdd_Country = GeoAdd_Country;
    //     this.GeoAdd_State = GeoAdd_State;
    //     this.GeoAdd_Locality = GeoAdd_Locality;
    //     this.GeoAdd_BlockHay = GeoAdd_BlockHay;
    // }
}

