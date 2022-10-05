package models.party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class Characteristic extends PanacheEntity implements HasIndividual {
	public String name;
	public String valueType;
	public String value;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "individual")
	public Individual individual;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "organization")
	public Organization organization;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "partyRole")
	public PartyRole partyRole;

	@Override
	public void setIndividual(Individual i) {
		individual = i;
	}
}
