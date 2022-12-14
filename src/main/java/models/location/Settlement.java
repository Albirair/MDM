package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
// import java.util.Set;
import java.util.Date;


@Entity
public class Settlement extends PanacheEntity{

    public String name;
    public String type;
    public String code;
    public long population;
    public long area;
    public Date date_of_population_estimation;

    @ManyToOne
    @JoinColumn(name = "settlement_state")
    @JsonBackReference(value = "settlement_state")
    public State settlement_state;
    @ManyToOne
    @JoinColumn(name = "GeoAdd_settlement")
    @JsonBackReference(value = "GeoAdd_settlement")
    public GeographicAddress GeoAdd_settlement;

    public Settlement() {
    }

    public Settlement( String name, String type, String code ,long population, long area, Date date_of_population_estimation, GeographicAddress GeoAdd_settlement) {

        this.name = name;
        this.type = type;
        this.code = code;
        this.population = population;
        this.area = area;
        this.date_of_population_estimation = date_of_population_estimation;
        this.GeoAdd_settlement = GeoAdd_settlement;
    }
}
