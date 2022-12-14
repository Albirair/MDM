package models.location;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import models.ModelBase;
import java.util.Set;
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

    @OneToMany
    @JsonManagedReference(value = "GeoAdd_GeoSubAdd")
    public Set <GeographicSubAddress> GeoAdd_GeoSubAdd;
    // Forign key:
    @OneToOne
    public GeographicLocation GeoAdd_geoLoca;
    @OneToMany
    @JsonManagedReference(value = "GeoAdd_Country")
    public Set <Country> GeoAdd_Country;
    @OneToMany
    @JsonManagedReference(value = "GeoAdd_State")
    public Set <State> GeoAdd_State;
    @OneToMany
    @JsonManagedReference(value = "GeoAdd_settlement")
    public Set <Settlement> GeoAdd_settlement;
    
}