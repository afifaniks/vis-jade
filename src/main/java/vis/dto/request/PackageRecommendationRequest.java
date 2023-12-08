package vis.dto.request;

public class PackageRecommendationRequest {

    String userEmail;

    String vehicleId;

    public PackageRecommendationRequest(String userEmail, String vehicleId) {
        this.userEmail = userEmail;
        this.vehicleId = vehicleId;
    }

    public PackageRecommendationRequest() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

}
