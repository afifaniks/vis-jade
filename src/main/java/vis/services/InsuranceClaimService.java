package vis.services;

import jade.lang.acl.UnreadableException;
import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

import java.io.IOException;

/***
 * This interface is used to claim insurance.
 */
public interface InsuranceClaimService {

	InsuranceClaimStatusSchema claimInsurance(ClaimRequestSchema claimRequest) throws IOException, UnreadableException;

}
