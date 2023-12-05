package vis.dto.request;

public class VehicleRegistrationRequest {

    String userEmail;

    String vehicleName;

    String vehicleModel;

    String vehicleType;

    String licenseNumber;

    String vehicleRegistrationNumber;

    String purchaseDate;

    String vehicleStatus;

    Integer mileage;

    public VehicleRegistrationRequest(String userEmail, String vehicleName, String vehicleModel, String vehicleType, String licenseNumber, String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus, Integer mileage) {
        this.userEmail = userEmail;
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.purchaseDate = purchaseDate;
        this.vehicleStatus = vehicleStatus;
        this.mileage = mileage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
