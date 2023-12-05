package vis.dto.request;

public class VehicleRegistrationRequest {

    String userEmail;

    String vehicleName;

    String vehicleModel;

    String vehicleType;

    String licenseNumber;

    String vehicle_registration_number;

    String purchaseDate;

    String vehicleStatus;

    Integer mileage;

    public VehicleRegistrationRequest(String userEmail, String vehicleName, String vehicleModel, String vehicleType, String licenseNumber, String vehicle_registration_number, String purchaseDate, String vehicleStatus, Integer mileage) {
        this.userEmail = userEmail;
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.vehicle_registration_number = vehicle_registration_number;
        this.purchaseDate = purchaseDate;
        this.vehicleStatus = vehicleStatus;
        this.mileage = mileage;
    }

    public VehicleRegistrationRequest() {
    }

    public String getEmailId() {
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

    public String getVehicle_registration_number() {
        return vehicle_registration_number;
    }

    public void setVehicle_registration_number(String vehicle_registration_number) {
        this.vehicle_registration_number = vehicle_registration_number;
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
