package vis.services;

import jade.lang.acl.UnreadableException;
import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

import java.io.IOException;

public interface InsuranceClaimService {

	InsuranceClaimStatusSchema claimInsurance(ClaimRequestSchema claimRequest) throws IOException, UnreadableException;

}
