package vis.dto.response;

public class UserResponse {
    String userId;
    String email;

    String name;

    String phone;

    String address;

    String dob;

    Double height;

    String gender;

    String eyeColor;

    String bloodGroup;

    public UserResponse() {
    }

    public UserResponse(String userId, String email, String name, String phone, String address, String dob, Double height, String gender, String eyeColor, String bloodGroup) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.height = height;
        this.gender = gender;
        this.eyeColor = eyeColor;
        this.bloodGroup = bloodGroup;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}

