package vis.services.schema;

import java.io.Serializable;

public class SubscriptionRequestSchema implements Serializable {

	private String userEmail;

	private String vehicleId;

	private String packageId;

	public SubscriptionRequestSchema(String userEmail, String vehicleId, String packageId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
		this.packageId = packageId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

}
