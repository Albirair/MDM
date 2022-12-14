package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.vertx.pgclient.impl.codec.DataType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class SubRole_attribute extends PanacheEntity{

    public String name;
    public String definition;
    public Set<DataType> data_type;
    public Set<String> value_set;
    public Boolean Is_Mandatory;
    public Set<String> alternative_lable;
    public Date source_date;

    @OneToOne(mappedBy = "Attr_uofm")
    public UnitOfMeasure Attr_uofm;

    
    @OneToMany
    @JsonManagedReference(value = "subRoleAttr_attrCatg")
    public Set <Continent> subRoleAttr_attrCatg;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "subRoleAttr_org")
    public SubRole_attribute subRoleAttr_org;

    public SubRole_attribute() {
    }

    public SubRole_attribute( String name, String definition, Set<DataType> data_type ,Set<String> value_set, Boolean Is_Mandatory, Set<String> alternative_lable, Date source_date) {

        this.name = name;
        this.definition = definition;
        this.data_type = data_type;
        this.value_set = value_set;
        this.Is_Mandatory = Is_Mandatory;
        this.alternative_lable = alternative_lable;
        this.source_date = source_date;
    }
}

