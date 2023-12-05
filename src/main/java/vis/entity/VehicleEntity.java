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
    private UUID id;

    UUID userEmail;
    String vehicle_name;
    String vehicle_model;
    String vehicle_type;
    String license_plate_number;
    String vehicle_registration_number;
    String purchase_date;
    String vehicle_status;
    float mileage;

    public VehicleEntity() {
    }

    public VehicleEntity(UUID userEmail, String vehicle_name, String vehicle_model, String vehicle_type, String license_plate_number, String vehicle_registration_number, String purchase_date, String vehicle_status, float mileage) {
        this.userEmail = userEmail;
        this.vehicle_name = vehicle_name;
        this.vehicle_model = vehicle_model;
        this.vehicle_type = vehicle_type;
        this.license_plate_number = license_plate_number;
        this.vehicle_registration_number = vehicle_registration_number;
        this.purchase_date = purchase_date;
        this.vehicle_status = vehicle_status;
        this.mileage = mileage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(UUID userEmail) {
        this.userEmail = userEmail;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getLicense_plate_number() {
        return license_plate_number;
    }

    public void setLicense_plate_number(String license_plate_number) {
        this.license_plate_number = license_plate_number;
    }

    public String getVehicle_registration_number() {
        return vehicle_registration_number;
    }

    public void setVehicle_registration_number(String vehicle_registration_number) {
        this.vehicle_registration_number = vehicle_registration_number;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(String vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", user_email=" + userEmail +
                ", vehicle_name='" + vehicle_name + '\'' +
                ", vehicle_model='" + vehicle_model + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", license_plate_number='" + license_plate_number + '\'' +
                ", vehicle_registration_number='" + vehicle_registration_number + '\'' +
                ", purchase_date='" + purchase_date + '\'' +
                ", vehicle_status='" + vehicle_status + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
