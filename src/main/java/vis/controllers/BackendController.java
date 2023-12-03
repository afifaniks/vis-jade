package vis.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vis.agents.AgentActionIdentifier;
import vis.agents.AgentIdentifier;
import vis.dto.request.*;
import vis.dto.response.*;
import vis.services.AgentGatewayService;
import vis.services.schema.AgentOperationStatusSchema;
import vis.services.schema.InsurancePackageSchema;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BackendController {

	private final AgentGatewayService gatewayService;

	private final Gson gson = new Gson();

	@Autowired
	public BackendController(AgentGatewayService gatewayService) throws ControllerException, InterruptedException {
		this.gatewayService = gatewayService;
	}

	@PostMapping("/login")
	public TokenResponse login(@RequestBody LoginRequest loginRequest) {
		AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.AUTHENTICATION, "login",
				gson.toJson(loginRequest));

		// GatewayResponseDto responseDto = gatewayService.request(action);
		return new TokenResponse("access_token", "refresh_token");
	}

	@PostMapping("/signup")
	public StatusResponse signup(@RequestBody SignupRequest signupDto) {
		AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.AUTHENTICATION, "signup",
				gson.toJson(signupDto));

		// GatewayResponseDto responseDto = gatewayService.request(action);
		// TODO: tbd implementation
		return new StatusResponse(200, "Signup successful");
	}

	@PostMapping("/register-vehicle")
	public VehicleRegistrationResponse registerVehicle(
			@RequestBody VehicleRegistrationRequest vehicleRegistrationRequest) {
		// TODO: To be implemented
		return new VehicleRegistrationResponse(200, "Registration successful");
	}

	@PostMapping("/get-packages")
	public ArrayList<PackageRecommendationResponse> getPackageRecommendation(
			@RequestBody PackageRecommendationRequest packageRecommendationRequest) {
		try {
			AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.CUSTOMER_ASSISTANT, "get-package",
					gson.toJson(packageRecommendationRequest));
			AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
					AgentOperationStatusSchema.class);
			String contents = operationStatus.getMessage();

			TypeToken<List<PackageRecommendationResponse>> token = new TypeToken<>() {
			};

			return gson.fromJson(contents, token.getType());
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@PostMapping("/subscribe-package")
	public StatusResponse subscribePackage(@RequestBody SubscribePackageRequest subscribePackageRequest) {
		try {
			AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.CUSTOMER_ASSISTANT, "subscribe",
					gson.toJson(subscribePackageRequest));
			AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
					AgentOperationStatusSchema.class);
			return new StatusResponse(operationStatus.getStatus(), operationStatus.getMessage());
		}
		catch (Exception e) {
			return new StatusResponse(500, "Subscription failed");
		}
	}

	@PostMapping("/claim-insurance")
	public ClaimResponse claimInsurance(@RequestBody ClaimRequest claimRequest) {
		// TODO: To be implemented
		return new ClaimResponse(200, "Insurance claim successful");
	}

}
