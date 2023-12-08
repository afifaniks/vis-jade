package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SubscribedPackageSchema extends InsurancePackageSchema implements Serializable {

	String id;

	Date subscribedOn;

	Date claimedOn;

	public SubscribedPackageSchema(String packageId, String packageName, String packageDescription, Double packagePrice,
			Integer tenure) {
		super(packageId, packageName, packageDescription, packagePrice, tenure);
	}

	public SubscribedPackageSchema(String id, String packageId, String packageName, String packageDescription,
			Double packagePrice, Integer tenure, Date subscribedOn, Date claimedOn) {
		super(packageId, packageName, packageDescription, packagePrice, tenure);
		this.id = id;
		this.subscribedOn = subscribedOn;
		this.claimedOn = claimedOn;
	}

	public SubscribedPackageSchema() {
	}

}
