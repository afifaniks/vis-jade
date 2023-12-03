package vis.services.schema;

public class RecommendationRequestSchema {

	private String userId;

	private String vehicleId;

	public RecommendationRequestSchema(String userId, String vehicleId) {
		this.userId = userId;
		this.vehicleId = vehicleId;
	}

	public RecommendationRequestSchema() {
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
