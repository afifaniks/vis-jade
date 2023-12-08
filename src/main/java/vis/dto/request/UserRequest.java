package vis.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@Email(message = "Invalid email address")
	String email;

	public UserRequest() {
	}

	public UserRequest(String email) {
		this.email = email;
	}

}
