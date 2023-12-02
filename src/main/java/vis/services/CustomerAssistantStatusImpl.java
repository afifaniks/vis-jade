package vis.services;

import vis.dto.InsurancePackageDto;
import vis.dto.UserInfoDto;
import vis.services.schema.Subscription;
import vis.services.schema.SubscriptionStatus;

import java.util.ArrayList;

public class CustomerAssistantStatusImpl implements CustomerAssistantService {

	@Override
	public ArrayList<InsurancePackageDto> getPackageRecommendation(UserInfoDto userInfo) {
		return null;
	}

	@Override
	public SubscriptionStatus subscribePackage(Subscription subscription) {
		// TODO: Write to database
		return new SubscriptionStatus(200, "Subscription successful");
	}

}
