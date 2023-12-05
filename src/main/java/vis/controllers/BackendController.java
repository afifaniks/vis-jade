package vis.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vis.agents.AgentActionIdentifier;
import vis.constants.AgentIdentifier;
import vis.dto.request.*;
import vis.dto.response.PackageRecommendationResponse;
import vis.dto.response.StatusResponse;
import vis.dto.response.TokenResponse;
import vis.dto.response.VehicleRegistrationResponse;
import vis.services.AgentGatewayService;
import vis.services.schema.AgentOperationStatusSchema;

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

		AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
				AgentOperationStatusSchema.class);

		return gson.fromJson(operationStatus.getMessage(), TokenResponse.class);
	}

	@PostMapping("/signup")
	public StatusResponse signup(@RequestBody SignupRequest signupDto) {
		AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.AUTHENTICATION, "signup",
				gson.toJson(signupDto));

		AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
				AgentOperationStatusSchema.class);
		return new StatusResponse(operationStatus.getStatus(), operationStatus.getMessage());
	}

	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/register-vehicle")
	public VehicleRegistrationResponse registerVehicle(
			@RequestBody VehicleRegistrationRequest vehicleRegistrationRequest) {
		AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.CUSTOMER_ASSISTANT, "vehicle-registration",
				gson.toJson(vehicleRegistrationRequest));

		AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
				AgentOperationStatusSchema.class);
		return new VehicleRegistrationResponse(operationStatus.getStatus(), operationStatus.getMessage());
	}

	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
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

	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
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

	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/claim-insurance")
	public StatusResponse claimInsurance(@RequestBody ClaimRequest claimRequest) {
		try {
			AgentActionIdentifier action = new AgentActionIdentifier(AgentIdentifier.INSURANCE_CLAIM, "claim",
					gson.toJson(claimRequest));
			AgentOperationStatusSchema operationStatus = gson.fromJson(gatewayService.request(action),
					AgentOperationStatusSchema.class);
			return new StatusResponse(operationStatus.getStatus(), operationStatus.getMessage());
		}
		catch (Exception e) {
			return new StatusResponse(500, "Insurance claim failed");
		}
	}

}
