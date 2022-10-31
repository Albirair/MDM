package models.resource;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@IdClass(LogicalPhysicalResourceId.class)
@Entity
public class LogicalPhysicalResource extends PanacheEntityBase {
	@Id
	@ManyToOne
	@JoinColumn
	@JsonBackReference("logicalResource")
	public LogicalResource logicalResource;
	@Id
	@ManyToOne
	@JoinColumn
	@JsonBackReference("physicalResource")
	public PhysicalResource physicalResource;
	public int typeOfLPDependency;
}