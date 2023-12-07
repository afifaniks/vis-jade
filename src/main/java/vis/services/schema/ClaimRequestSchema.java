package vis.services.schema;

import java.io.Serializable;

public class ClaimRequestSchema implements Serializable {

	String userEmail;

	String subscriptionId;

	public ClaimRequestSchema(String userEmail, String subscriptionId) {
		this.userEmail = userEmail;
		this.subscriptionId = subscriptionId;
	}

	public ClaimRequestSchema() {
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
