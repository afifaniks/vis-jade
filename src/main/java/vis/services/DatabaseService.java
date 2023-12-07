package vis.services;

import vis.services.schema.*;

import java.util.ArrayList;
import java.util.UUID;

public interface DatabaseService {

    boolean login(String email, String password);

    boolean signup(SignupRequestSchema userData);

    ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId);

    boolean subscribe(SubscriptionSchema subscriptionSchema);

    boolean vehicleRegistration(VehicleRegistrationSchema vehicleRegistrationData);

    GetUserRequestSchema getUserRequest(String email);

}
