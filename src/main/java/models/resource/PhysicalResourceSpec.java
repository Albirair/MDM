package models.resource;

import javax.persistence.*;

@Entity
public class PhysicalResourceSpec extends ResourceSpecification {
	public String modelNumber;
	public String vendorName;
	public String skuNumber;
	public String partNumber;
}