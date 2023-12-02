package vis.services;

import vis.dto.InsurancePackageDto;
import vis.dto.UserInfoDto;
import vis.services.schema.Subscription;
import vis.services.schema.SubscriptionStatus;

import java.util.ArrayList;

public interface CustomerAssistantService {

	ArrayList<InsurancePackageDto> getPackageRecommendation(UserInfoDto userInfo);

	SubscriptionStatus subscribePackage(Subscription subscription);

}
