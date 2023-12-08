package vis.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {

	String username;

	String password;

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
