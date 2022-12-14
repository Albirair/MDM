package models.location;

// import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;
// import java.util.Set;



@Entity
public class AttributeCatogary extends PanacheEntity{

    public String attribute_catogary;
    public String description;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "subRoleAttr_attrCatg")
    public SubRole_attribute subRoleAttr_attrCatg;

    public AttributeCatogary() {
    }

    public AttributeCatogary( String attribute_catogary, String description, String print ,String c_s,String c_i, Boolean m, long definition_of_value, String definition_of_unit, String uses,SubRole_attribute subRoleAttr_attrCatg) {

        this.attribute_catogary = attribute_catogary;
        this.description = description;
        this.subRoleAttr_attrCatg = subRoleAttr_attrCatg;
    }
}

