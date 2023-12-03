package vis.services.schema;

public class SubscriptionSchema {

	private String userId;

	private String vehicleId;

	private String packageId;

	public SubscriptionSchema(String userId, String vehicleId, String packageId) {
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.packageId = packageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
