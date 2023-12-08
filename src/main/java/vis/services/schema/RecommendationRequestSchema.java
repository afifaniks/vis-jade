package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RecommendationRequestSchema implements Serializable {

	private String userEmail;

	private String vehicleId;

	public RecommendationRequestSchema(String userEmail, String vehicleId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
	}

	public RecommendationRequestSchema() {
	}

}
