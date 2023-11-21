package vis.services;

public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public void login() {
        System.out.println("Login called");
    }

    @Override
    public void signup() {
        System.out.println("Signup called");
    }
}
