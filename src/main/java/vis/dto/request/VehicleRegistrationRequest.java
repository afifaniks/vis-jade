package vis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRegistrationRequest {

	@Email(message = "Should be a valid email address")
	String userEmail;

	@NotBlank(message = "Can not be blank")
	String vehicleName;

	@NotBlank(message = "Can not be blank")
	String vehicleModel;

	@NotBlank(message = "Can not be blank")
	String vehicleType;

	@NotBlank(message = "Can not be blank")
	String licenseNumber;

	@NotBlank(message = "Can not be blank")
	String vehicleRegistrationNumber;

	@NotBlank(message = "Can not be blank")
	String purchaseDate;

	@NotBlank(message = "Can not be blank")
	String vehicleStatus;

	Integer mileage;

	public VehicleRegistrationRequest(String userEmail, String vehicleName, String vehicleModel, String vehicleType,
			String licenseNumber, String vehicleRegistrationNumber, String purchaseDate, String vehicleStatus,
			Integer mileage) {
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

}
