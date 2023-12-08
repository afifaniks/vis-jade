package vis.dto.request;

public class SubscribePackageRequest {

    String userEmail;

    String vehicleId;

    String packageId;

    public SubscribePackageRequest(String userEmail, String vehicleId, String packageId) {
        this.userEmail = userEmail;
        this.vehicleId = vehicleId;
        this.packageId = packageId;
    }

    public SubscribePackageRequest() {
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

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

}
