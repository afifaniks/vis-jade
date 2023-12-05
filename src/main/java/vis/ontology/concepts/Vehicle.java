package vis.ontology.concepts;

import jade.content.Concept;

public class Vehicle implements Concept {

	String vehicleId;
	String userEmail;
	String vehicleName;
	String vehicleModel;
	String vehicleType;
	String licenseNumber;
	String vehicle_registration_number;
	String purchaseDate;
	String vehicleStatus;
	float mileage;

	public Vehicle() {
	}

	public Vehicle(String userEmail, String vehicleName, String vehicleModel, String vehicleType, String licenseNumber, String vehicle_registration_number, String purchaseDate, String vehicleStatus, float mileage) {
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

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}














}
