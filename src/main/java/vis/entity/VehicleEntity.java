package vis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import vis.constants.DBTableNames;

import java.io.Serializable;

@Entity
@Table(name = DBTableNames.VEHICLE)
public class VehicleEntity implements DBEntity, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	String userEmail;

	String vehicleName;

	String vehicleModel;

	String vehicleType;

	String licenseNumberPlate;

	String vehicleRegistrationNumber;

	String purchaseDate;

	String vehicleStatus;

	float mileage;

	String drivingLicense;

	String licenseDateOfExpiry;

	public VehicleEntity() {
	}

	public VehicleEntity(String id, String userEmail, String vehicleName, String vehicleModel, String vehicleType,
			String licenseNumberPlate, String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus,
			float mileage, String drivingLicense, String licenseDateOfExpiry) {
		this.id = id;
		this.userEmail = userEmail;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleType = vehicleType;
		this.licenseNumberPlate = licenseNumberPlate;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.purchaseDate = purchaseDate;
		this.vehicleStatus = vehicleStatus;
		this.mileage = mileage;
		this.drivingLicense = drivingLicense;
		this.licenseDateOfExpiry = licenseDateOfExpiry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		id = id;
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

	public String getLicenseNumberPlate() {
		return licenseNumberPlate;
	}

	public void setLicenseNumberPlate(String licenseNumberPlate) {
		this.licenseNumberPlate = licenseNumberPlate;
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
