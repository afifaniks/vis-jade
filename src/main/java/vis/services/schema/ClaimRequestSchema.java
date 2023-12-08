package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClaimRequestSchema implements Serializable {

	String userEmail;

	String subscriptionId;

	public ClaimRequestSchema(String userEmail, String subscriptionId) {
		this.userEmail = userEmail;
		this.subscriptionId = subscriptionId;
	}

	public ClaimRequestSchema() {
	}

}
