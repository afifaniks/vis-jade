package vis.services.schema;

public class ClaimRequestSchema {

	String userId;

	String subscriptionId;

	public ClaimRequestSchema(String userId, String subscriptionId) {
		this.userId = userId;
		this.subscriptionId = subscriptionId;
	}

	public ClaimRequestSchema() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
