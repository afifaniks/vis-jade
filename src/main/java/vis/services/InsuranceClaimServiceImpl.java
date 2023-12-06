package vis.services;

import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

public class InsuranceClaimServiceImpl implements InsuranceClaimService {

	@Override
	public InsuranceClaimStatusSchema claimInsurance(ClaimRequestSchema claimRequest) {
		// TODO: Connect with DB
		return new InsuranceClaimStatusSchema(200, "Insurance claim successfuls");
	}

}
