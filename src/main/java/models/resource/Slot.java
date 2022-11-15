package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Slot extends HolderAtomic {
	public boolean hasAdapter;
	public int number;
	public int purpose;
	public String purposeDescription;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("slot")
	public Slot slot;
	@OneToMany(mappedBy = "slot", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("slot")
	public Set<Slot> contains;
}