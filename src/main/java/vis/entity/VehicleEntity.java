package vis.entity;

import jakarta.persistence.*;
import vis.constants.DBTableNames;

import java.util.UUID;
import java.io.Serializable;

@Entity
@Table(name = DBTableNames.VEHICLE)
public class VehicleEntity implements DBEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    String userEmail;
    String vehicleName;
    String vehicleModel;
    String vehicleType;
    String licenseNumberPlate;
    String vehicleRegistrationNumber;
    String purchaseDate;
    String vehicleStatus;
    float mileage;

    public VehicleEntity() {
    }

    public VehicleEntity(String userEmail, String vehicleName, String vehicleModel, String vehicleType, String licenseNumberPlate, String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus, float mileage) {
        this.userEmail = userEmail;
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.licenseNumberPlate = licenseNumberPlate;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.purchaseDate = purchaseDate;
        this.vehicleStatus = vehicleStatus;
        this.mileage = mileage;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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
}
