package vis.entity;

import jakarta.persistence.*;
import vis.constants.DBTableNames;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = DBTableNames.USER)
public class UserEntity implements DBEntity, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(unique = true)
	String email;

	String name;

	String password;

	String phone;

	String address;

	String dob;

	Double height;

	String gender;

	String eyeColor;

	String bloodGroup;

	public UserEntity() {
	}

	public UserEntity(String email, String name, String password, String phone, String address, String dob,
			Double height, String gender, String eyeColor, String bloodGroup) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.height = height;
		this.gender = gender;
		this.eyeColor = eyeColor;
		this.bloodGroup = bloodGroup;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + ", password='" + password
				+ '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", dob='" + dob + '\''
				+ ", height=" + height + ", gender='" + gender + '\'' + ", eyeColor='" + eyeColor + '\''
				+ ", bloodGroup='" + bloodGroup + '\'' + '}';
	}

}