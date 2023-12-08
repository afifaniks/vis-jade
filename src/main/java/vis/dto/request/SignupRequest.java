package vis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Getter
@Setter
public class SignupRequest implements Serializable {

	@Email(message = "Invalid email")
	String email;

	@NotBlank(message = "Can not be blank")
	@Size(min = 3, message = "Minimum length should be 3")
	String name;

	@NotBlank(message = "Can not be blank")
	@Size(min = 5, message = "Minimum length should be 5")
	String password;

	@NotBlank(message = "Can not be blank")
	@Size(min = 6, message = "Minimum length should be 6")
	String phone;

	@NotBlank(message = "Can not be blank")
	@Size(min = 3, message = "Minimum length should be 3")
	String address;

	@NotBlank(message = "Can not be blank")
	@Size(min = 3, message = "Minimum length should be 3")
	String dob;

	@Range(min = 100, message = "Height should be at least 100 cm")
	Double height;

	@NotBlank(message = "Can not be blank")
	String gender;

	@NotBlank(message = "Can not be blank")
	String eyeColor;

	@NotBlank(message = "Can not be blank")
	String bloodGroup;

	public SignupRequest() {
	}

	public SignupRequest(String email, String name, String password, String phone, String address, String dob,
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
