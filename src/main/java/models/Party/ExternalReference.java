package models.party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class ExternalReference extends PanacheEntity implements HasIndividual {
	public String type;
	public String name;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "individual")
	public Individual individual;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "organization")
	public Organization organization;

	@Override
	public void setIndividual(Individual i) {
		individual = i;
	}
}
