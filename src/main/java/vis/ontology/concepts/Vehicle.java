package vis.ontology.concepts;

import jade.content.Concept;

public class Vehicle implements Concept {

	String vehicleId;

	String userEmail;

	String vehicleName;

	String vehicleModel;

	String vehicleType;

	String licenseNumber;

	String vehicleRegistrationNumber;

	String purchaseDate;

	String vehicleStatus;

	float mileage;

	String drivingLicense;

	String licenseDateOfExpiry;

	public Vehicle() {
	}

	public Vehicle(String userEmail, String vehicleName, String vehicleModel, String vehicleType, String licenseNumber,
			String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus, float mileage,
			String drivingLicense, String licenseDateOfExpiry) {
		this.userEmail = userEmail;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleType = vehicleType;
		this.licenseNumber = licenseNumber;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.purchaseDate = purchaseDate;
		this.vehicleStatus = vehicleStatus;
		this.mileage = mileage;
		this.drivingLicense = drivingLicense;
		this.licenseDateOfExpiry = licenseDateOfExpiry;
	}

	public Vehicle(String vehicleId, String userEmail) {
		this.vehicleId = vehicleId;
		this.userEmail = userEmail;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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

	public void setVehicleRegistrationNumber(String vehicle_registration_number) {
		this.vehicleRegistrationNumber = vehicle_registration_number;
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

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getLicenseDateOfExpiry() {
		return licenseDateOfExpiry;
	}

	public void setLicenseDateOfExpiry(String licenseDateOfExpiry) {
		this.licenseDateOfExpiry = licenseDateOfExpiry;
	}

}
