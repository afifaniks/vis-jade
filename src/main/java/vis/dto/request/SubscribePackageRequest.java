package vis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribePackageRequest {

	@Email(message = "Should be a valid email address")
	String userEmail;

	@NotBlank(message = "Can not be blank")
	String vehicleId;

	@NotBlank(message = "Can not be blank")
	String packageId;

	public SubscribePackageRequest(String userEmail, String vehicleId, String packageId) {
		this.userEmail = userEmail;
		this.vehicleId = vehicleId;
		this.packageId = packageId;
	}

	public SubscribePackageRequest() {
	}

}
