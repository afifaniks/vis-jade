package vis.services;

import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.RecommendationRequestSchema;
import vis.services.schema.SubscriptionSchema;
import vis.services.schema.SubscriptionStatusSchema;

import java.util.ArrayList;

public class CustomerAssistantStatusImpl implements CustomerAssistantService {

	@Override
	public ArrayList<InsurancePackageSchema> getPackageRecommendation(
			RecommendationRequestSchema recommendationRequestSchema) {
		return new ArrayList<>() {
			{
				add(new InsurancePackageSchema("1", "Test Package", "Description", 33.44, 4));
				add(new InsurancePackageSchema("2", "Test Package 2", "Description 2", 332.44, 41));
			}
		};
	}

	@Override
	public SubscriptionStatusSchema subscribePackage(SubscriptionSchema subscriptionSchema) {
		// TODO: Write to database
		return new SubscriptionStatusSchema(200, "Subscription successful");
	}

}
