package vis.services;

import vis.services.schema.*;

import java.util.ArrayList;

public interface DatabaseService {

	boolean login(String email, String password);

	boolean signup(SignupRequestSchema userData);

	ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId);

	boolean subscribe(SubscriptionRequestSchema subscriptionRequestSchema);

	boolean vehicleRegistration(VehicleRegistrationSchema vehicleRegistrationData);

	UserProfileSchema getUserRequest(String email);

}
