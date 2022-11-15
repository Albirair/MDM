package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class IndividualIdentification extends PanacheEntity {
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
}