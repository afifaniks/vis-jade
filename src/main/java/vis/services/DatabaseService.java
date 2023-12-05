package vis.services;

import vis.dto.request.VehicleRegistrationRequest;
import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.SubscriptionSchema;
import vis.services.schema.VehicleRegistrationSchema;

import java.util.ArrayList;

public interface DatabaseService {

	boolean login(String email, String password);

	boolean signup(SignupRequestSchema userData);

	ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId);

	boolean subscribe(SubscriptionSchema subscriptionSchema);

	boolean vehicleRegistration(VehicleRegistrationSchema vehicleRegistrationData);

}
