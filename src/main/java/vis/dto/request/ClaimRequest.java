package vis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimRequest {

	@Email(message = "Should be a valid email address")
	String userEmail;

	@NotBlank(message = "Can not be blank")
	String subscriptionId;

	public ClaimRequest(String userEmail, String subscriptionId) {
		this.userEmail = userEmail;
		this.subscriptionId = subscriptionId;
	}

	public ClaimRequest(String userEmail) {
		this.userEmail = userEmail;
	}

}
