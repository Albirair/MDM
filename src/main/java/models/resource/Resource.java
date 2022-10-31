package models.resource;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import models.ModelBase;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resource extends ModelBase {
	@ManyToOne
	@JoinColumn
	@JsonBackReference("resourceSpecification")
	public ResourceSpecification resourceSpecification;
}