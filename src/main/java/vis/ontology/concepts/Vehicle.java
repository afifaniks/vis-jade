package vis.ontology.concepts;

import jade.content.Concept;

public class Vehicle implements Concept {

	String vehicleId;
	String userId;
	String vehicleName;
	String vehicleModel;
	String vehicleType;
	String licenseNumber;
	String registrationNumber;
	String purchaseDate;
	String vehicleStatus;
	float mileage;

	public Vehicle() {
	}

	public Vehicle(String registrationNumber, String licenseNumber, String vehicleName, String vehicleModel, String vehicleType, String purchaseDate, String userId) {
		this.registrationNumber = registrationNumber;
		this.licenseNumber = licenseNumber;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleType = vehicleType;
		this.purchaseDate = purchaseDate;
		this.userId = userId;
	}

	public Vehicle(String vehicleId, String userId) {
		this.vehicleId = vehicleId;
		this.userId = userId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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
