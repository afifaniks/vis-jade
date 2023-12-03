package vis.services.schema;

import java.io.Serializable;

public class RecommendationRequestSchema implements Serializable {

	private String userEmail;

	private String vehicleId;

	public RecommendationRequestSchema(String userEmail, String vehicleId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
	}

	public RecommendationRequestSchema() {
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

}
