package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class ContactMedium extends PanacheEntity implements HasIndividual {
	public String type;
	public boolean preferred;
	public Date validFrom;
	public Date validUntil;
	@OneToOne(mappedBy = "contactMedium", cascade = CascadeType.ALL, orphanRemoval = true)
	private MediumCharachteristic charachteristic;
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
