package vis.services;

import jade.lang.acl.UnreadableException;
import vis.services.schema.*;

import java.io.IOException;
import java.util.ArrayList;

public interface CustomerAssistantService {

	ArrayList<InsurancePackageSchema> getPackageRecommendation(RecommendationRequestSchema recommendationRequestSchema)
			throws UnreadableException, IOException;

	SubscriptionStatusSchema subscribePackage(SubscriptionSchema subscriptionSchema) throws IOException, UnreadableException;

	VehicleRegistrationStatusSchema registerVehicle(VehicleRegistrationSchema vehicleRegistrationSchema) throws UnreadableException, IOException;

}
