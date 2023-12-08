package vis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageRecommendationRequest {

	@Email(message = "Should be a valid email address")
	String userEmail;

	@NotBlank(message = "Vehicle Id is required")
	String vehicleId;

	public PackageRecommendationRequest(String userEmail, String vehicleId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
	}

	public PackageRecommendationRequest() {
	}

}
