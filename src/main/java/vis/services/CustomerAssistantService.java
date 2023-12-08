package vis.services;

import jade.lang.acl.UnreadableException;
import vis.services.schema.*;

import java.io.IOException;
import java.util.ArrayList;

/***
 * This CustomerAssistantService is the service class
 * which helps to get the services done which are directly related to the customer.
 */
public interface CustomerAssistantService {

    ArrayList<InsurancePackageSchema> getPackageRecommendation(RecommendationRequestSchema recommendationRequestSchema) throws UnreadableException, IOException;

    SubscriptionStatusSchema subscribePackage(SubscriptionRequestSchema subscriptionRequestSchema) throws IOException, UnreadableException;

    VehicleRegistrationStatusSchema registerVehicle(VehicleRegistrationSchema vehicleRegistrationSchema) throws UnreadableException, IOException;

    UserProfileSchema getUser(GetUserRequestSchema getUserRequestSchema) throws UnreadableException, IOException;

}
