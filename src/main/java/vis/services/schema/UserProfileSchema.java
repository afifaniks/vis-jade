package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class UserProfileSchema implements Serializable {

	String id;

	String email;

	String name;

	String phone;

	String address;

	String dob;

	Double height;

	String gender;

	String eyeColor;

	String bloodGroup;

	ArrayList<VehicleSchema> vehicles = new ArrayList<>();

	ArrayList<SubscribedPackageSchema> subscribedPackages = new ArrayList<>();

	public UserProfileSchema(String id, String email, String name, String phone, String address, String dob,
			Double height, String gender, String eyeColor, String bloodGroup, ArrayList<VehicleSchema> vehicles,
			ArrayList<SubscribedPackageSchema> subscribedPackages) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.height = height;
		this.gender = gender;
		this.eyeColor = eyeColor;
		this.bloodGroup = bloodGroup;
		this.vehicles = vehicles;
		this.subscribedPackages = subscribedPackages;
	}

	public UserProfileSchema() {
	}

}
