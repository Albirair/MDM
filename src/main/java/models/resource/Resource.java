package models.resource;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import models.ModelBase;

@Entity
public class Resource extends ModelBase {
	@ManyToOne
	@JoinColumn
	@JsonBackReference("resourceSpecification")
	public ResourceSpecification resourceSpecification;
}