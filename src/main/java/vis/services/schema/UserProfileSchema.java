package vis.services.schema;

import java.io.Serializable;
import java.util.ArrayList;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public ArrayList<VehicleSchema> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<VehicleSchema> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<SubscribedPackageSchema> getSubscribedPackages() {
		return subscribedPackages;
	}

	public void setSubscribedPackages(ArrayList<SubscribedPackageSchema> subscribedPackages) {
		this.subscribedPackages = subscribedPackages;
	}

}
