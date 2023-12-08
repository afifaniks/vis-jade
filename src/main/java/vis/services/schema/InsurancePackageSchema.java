package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InsurancePackageSchema implements Serializable {

	String packageId;

	String packageName;

	String packageDescription;

	Double packagePrice;

	Integer tenure;

	public InsurancePackageSchema(String packageId, String packageName, String packageDescription, Double packagePrice,
			Integer tenure) {
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.packagePrice = packagePrice;
		this.tenure = tenure;
	}

	public InsurancePackageSchema() {
	}

}
