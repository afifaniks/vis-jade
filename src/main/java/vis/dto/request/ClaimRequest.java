package vis.dto.request;

public class ClaimRequest {

	String userId;

	String subscriptionId;

	public ClaimRequest(String userId, String subscriptionId) {
		this.userId = userId;
		this.subscriptionId = subscriptionId;
	}

	public ClaimRequest(String userId) {
		this.userId = userId;
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
