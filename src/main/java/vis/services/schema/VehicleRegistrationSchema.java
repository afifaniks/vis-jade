package vis.services.schema;

import java.io.Serializable;
import java.util.UUID;

public class VehicleRegistrationSchema implements Serializable {

    UUID user_id;
    String vehicle_name;
    String vehicle_model;
    String vehicle_type;
    String license_number_plate;
    String vehicle_registration_number;
    String purchase_date;
    String vehicle_status;
    float mileage;

    public VehicleRegistrationSchema() {
    }

    public VehicleRegistrationSchema(UUID user_id, String vehicle_name, String vehicle_model, String vehicle_type, String license_number_plate, String vehicle_registration_number, String purchase_date, String vehicle_status, float mileage) {
        this.user_id = user_id;
        this.vehicle_name = vehicle_name;
        this.vehicle_model = vehicle_model;
        this.vehicle_type = vehicle_type;
        this.license_number_plate = license_number_plate;
        this.vehicle_registration_number = vehicle_registration_number;
        this.purchase_date = purchase_date;
        this.vehicle_status = vehicle_status;
        this.mileage = mileage;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
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

    public String getLicense_number_plate() {
        return license_number_plate;
    }

    public void setLicense_number_plate(String license_number_plate) {
        this.license_number_plate = license_number_plate;
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
}

