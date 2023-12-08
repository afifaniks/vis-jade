package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SubscriptionRequestSchema implements Serializable {

	private String userEmail;

	private String vehicleId;

	private String packageId;

	public SubscriptionRequestSchema(String userEmail, String vehicleId, String packageId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
		this.packageId = packageId;
	}

}
