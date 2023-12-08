package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VehicleSchema implements Serializable {

	String id;

	String vehicleName;

	String vehicleModel;

	String vehicleType;

	String licenseNumberPlate;

	String vehicleRegistrationNumber;

	String purchaseDate;

	String vehicleStatus;

	float mileage;

	public VehicleSchema(String vehicleName, String vehicleModel, String vehicleType, String licenseNumberPlate,
			String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus, float mileage) {
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleType = vehicleType;
		this.licenseNumberPlate = licenseNumberPlate;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.purchaseDate = purchaseDate;
		this.vehicleStatus = vehicleStatus;
		this.mileage = mileage;
	}

	public VehicleSchema() {
	}

}
