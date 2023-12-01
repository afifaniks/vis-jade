package vis.controllers;

import com.google.gson.Gson;
import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vis.agents.AgentType;
import vis.agents.AgentAction;
import vis.dto.GatewayResponseDto;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.request.ClaimRequest;
import vis.dto.request.PackageRecommendationRequest;
import vis.dto.request.VehicleRegistrationRequest;
import vis.dto.response.*;
import vis.services.AgentGatewayService;

import java.util.ArrayList;

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
		AgentAction action = new AgentAction(AgentType.AUTHENTICATION, "login", gson.toJson(loginRequest));

//		GatewayResponseDto responseDto = gatewayService.request(action);
		return new TokenResponse(
				"access_token",
				"refresh_token"
		);
	}

	@PostMapping("/signup")
	public SignupResponse signup(@RequestBody SignupRequest signupDto) {
		AgentAction action = new AgentAction(AgentType.AUTHENTICATION, "signup", gson.toJson(signupDto));

//		GatewayResponseDto responseDto = gatewayService.request(action);
		// TODO: tbd implementation
		return new SignupResponse(
				200,
				"Signup successful"
		);
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
		// TODO: To be implemented
		return new ArrayList<>() {
			{
				add(new PackageRecommendationResponse("Test Package", "Description", 33.44, 4));
			}
		};
	}

	@PostMapping("/claim-insurance")
	public ClaimResponse claimInsurance(@RequestBody ClaimRequest claimRequest) {
		// TODO: To be implemented
		return new ClaimResponse(200, "Insurance claim successful");
	}

}
