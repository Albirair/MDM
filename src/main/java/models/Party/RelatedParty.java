package models.party;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class RelatedParty extends PanacheEntity implements HasIndividual {
	public String href;
	public String name;
	public String role;
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
	@OneToOne(mappedBy = "engageParty")
	private PartyRole engagedRole;

	@Override
	public void setIndividual(Individual i) {
		individual = i;
	}
}
