package schema;

import java.util.Date;

public class User {

    private long user_id;
    private String email;
    private String password;
    private Date dob;
    private String gender;
    private int height;
    private String eye_color;
    private String blood_group;
    private String current_address;

    // Getter and setter methods

    public Long getUserId() {
        return user_id;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDOB() {
        return dob;
    }

    public void setDOB(Date dob) {
        this.dob = dob;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public String getEyeColor() {
        return eye_color;
    }

    public void setEyeColor(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBloodGroup() {
        return blood_group;
    }

    public void setBloodGroup(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getCurrentAddress() {
        return current_address;
    }

    public void setCurrentAddress(String current_address) {
        this.current_address = current_address;
    }

}
