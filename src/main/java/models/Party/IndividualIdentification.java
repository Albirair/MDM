package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class IndividualIdentification extends PanacheEntity implements HasIndividual {
	public String identificationId;
	public String identificationType;
	public String issuingAuthority;
	public Date validFrom;
	public Date validUntil;
	@OneToOne(mappedBy = "individualIdentification", cascade = CascadeType.ALL, orphanRemoval = true)
	private AttachmentRefOrValue attachment;
	@ManyToOne
	@JoinColumn
	@JsonBackReference(value = "individual")
	public Individual individual;

	@Override
	public void setIndividual(Individual i) {
		individual = i;
	}
}
