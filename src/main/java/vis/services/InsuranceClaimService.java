package vis.services;

import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

public interface InsuranceClaimService {

	InsuranceClaimStatusSchema claimInsurance(ClaimRequestSchema claimRequest);

}
