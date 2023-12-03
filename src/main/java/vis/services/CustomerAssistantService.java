package vis.services;

import jade.lang.acl.UnreadableException;
import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.RecommendationRequestSchema;
import vis.services.schema.SubscriptionSchema;
import vis.services.schema.SubscriptionStatusSchema;

import java.io.IOException;
import java.util.ArrayList;

public interface CustomerAssistantService {

	ArrayList<InsurancePackageSchema> getPackageRecommendation(RecommendationRequestSchema recommendationRequestSchema) throws UnreadableException, IOException;

	SubscriptionStatusSchema subscribePackage(SubscriptionSchema subscriptionSchema);

}
