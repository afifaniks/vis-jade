package vis.dto.request;

public class ClaimRequest {

    String userEmail;

    String subscriptionId;

    public ClaimRequest(String userEmail, String subscriptionId) {
        this.userEmail = userEmail;
        this.subscriptionId = subscriptionId;
    }

    public ClaimRequest(String userEmail) {
        this.userEmail = userEmail;
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
