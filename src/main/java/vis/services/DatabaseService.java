package vis.services;

import vis.services.schema.*;

import java.util.ArrayList;

/***
* This interface is used to interact with the database.
 */
public interface DatabaseService {

    boolean login(String email, String password);

    boolean signup(SignupRequestSchema userData);

    ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId);

    boolean subscribe(SubscriptionRequestSchema subscriptionRequestSchema);

    boolean vehicleRegistration(VehicleRegistrationSchema vehicleRegistrationData);

    UserProfileSchema getUserRequest(String email);

    boolean claimInsurance(ClaimRequestSchema claimRequestSchema);

}
