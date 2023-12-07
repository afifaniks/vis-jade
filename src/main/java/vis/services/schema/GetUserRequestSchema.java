package vis.services.schema;

import java.io.Serializable;

public class GetUserRequestSchema implements Serializable {

	public GetUserRequestSchema() {
	}

	String email;

	public GetUserRequestSchema(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
