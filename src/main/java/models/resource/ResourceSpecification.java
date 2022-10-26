package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import models.ModelBase;

public class ResourceSpecification extends ModelBase {
	@OneToMany(mappedBy = "resourceSpecification", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("resourceSpecification")
	public Set<Resource> specifies;
}