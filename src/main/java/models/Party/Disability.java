package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class Disability extends PanacheEntity implements HasIndividual {
	public String code;
	public String name;
	public Date validFrom;
	public Date validUntil;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "individual")
	public Individual individual;

	@Override
	public void setIndividual(Individual i) {
		individual = i;
	}
}
