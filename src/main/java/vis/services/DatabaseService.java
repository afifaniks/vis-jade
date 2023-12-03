package vis.services;

import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.SignupRequestSchema;

import java.util.ArrayList;

public interface DatabaseService {
	boolean login(String email, String password);
	boolean signup(SignupRequestSchema userData);
	ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId);
	boolean subscribe(String userEmail, String vehicleId, String packageId);
}
