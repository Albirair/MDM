package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class UnitOfMeasure extends PanacheEntity{

    public String name;
    public String kind_of_quantity;
    public String common_code;
    public String symbol;
    public String description;
    public String conversion_factor_to_SI;

    @OneToOne(mappedBy = "Attr_uofm")
    @JoinColumn
    public SubRole_attribute Attr_uofm;

    public UnitOfMeasure() {
    }

    public UnitOfMeasure( String name, String kind_of_quantity, String common_code ,String symbol,String description, String conversion_factor_to_SI, SubRole_attribute Attr_uofm) {

        this.name = name;
        this.kind_of_quantity = kind_of_quantity;
        this.common_code = common_code;
        this.symbol = symbol;
        this.description = description;
        this.conversion_factor_to_SI = conversion_factor_to_SI;
        this.Attr_uofm = Attr_uofm;
    }
}

