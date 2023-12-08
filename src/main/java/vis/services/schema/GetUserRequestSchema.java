package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetUserRequestSchema implements Serializable {

	public GetUserRequestSchema() {
	}

	String email;

	public GetUserRequestSchema(String email) {
		this.email = email;
	}

}
