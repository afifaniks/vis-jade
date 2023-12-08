package vis.dto.request;

public class UserRequest {

    String email;

    public UserRequest() {
    }

    public UserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
