package vis.services;

import vis.dto.InsurancePackageDto;
import vis.dto.UserInfoDto;

import java.util.ArrayList;

public interface CustomerAssistantService {

	ArrayList<InsurancePackageDto> getPackageRecommendation(UserInfoDto userInfo);

}
