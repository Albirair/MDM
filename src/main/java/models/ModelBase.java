package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The base class intended to be extended by persistent classes (models).
 * It just adds an id to {@code ModelBaseWithoutId}
 */
@MappedSuperclass
public abstract class ModelBase extends ModelBaseWithoutId {
	@Id
	@GeneratedValue
	@XmlTransient
	public long id;

	@Override
	public long getId() {
		return id;
	}
}