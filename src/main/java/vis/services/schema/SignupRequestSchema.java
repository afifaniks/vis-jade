package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SignupRequestSchema implements Serializable {

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

	public SignupRequestSchema() {
	}

	public SignupRequestSchema(String email, String name, String password, String phone, String address, String dob,
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

}
