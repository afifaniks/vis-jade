package vis.services;

import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.RecommendationRequestSchema;
import vis.services.schema.SubscriptionSchema;
import vis.services.schema.SubscriptionStatusSchema;

import java.util.ArrayList;

public interface CustomerAssistantService {

	ArrayList<InsurancePackageSchema> getPackageRecommendation(RecommendationRequestSchema recommendationRequestSchema);

	SubscriptionStatusSchema subscribePackage(SubscriptionSchema subscriptionSchema);

}
