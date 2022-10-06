package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.HasIndividual;

@Entity
public class OtherNameIndividual extends PanacheEntity implements HasIndividual {
	public String aristoctraticTitle;
	public String familyName;
	public String familyNamePrefix;
	public String formattedName;
	public String fullName;
	public String generation;
	public String givenName;
	public String legalName;
	public String middleName;
	public String preferredGivenName;
	public String title;
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
