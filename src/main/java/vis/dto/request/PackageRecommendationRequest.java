package vis.dto.request;

public class PackageRecommendationRequest {

	String userId;

	String vehicleId;

	public PackageRecommendationRequest(String userId, String vehicleId) {
		this.userId = userId;
		this.vehicleId = vehicleId;
	}

	public PackageRecommendationRequest() {
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

}
