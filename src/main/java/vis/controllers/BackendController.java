package vis.controllers;

import com.google.gson.Gson;
import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vis.agents.AgentType;
import vis.agents.AgentAction;
import vis.dto.GatewayResponseDto;
import vis.dto.LoginDto;
import vis.dto.SignupDto;
import vis.services.AgentGatewayService;

@RestController
public class BackendController {

	private final AgentGatewayService gatewayService;

	private final Gson gson = new Gson();

	@Autowired
	public BackendController(AgentGatewayService gatewayService) throws ControllerException, InterruptedException {
		this.gatewayService = gatewayService;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginDto loginDto) {
		AgentAction action = new AgentAction(AgentType.AUTHENTICATION, "login", gson.toJson(loginDto));

		GatewayResponseDto responseDto = gatewayService.request(action);
		return responseDto.getMessage();
	}

	@PostMapping("/signup")
	public String signup(@RequestBody SignupDto signupDto) {
		AgentAction action = new AgentAction(AgentType.AUTHENTICATION, "signup", gson.toJson(signupDto));

		GatewayResponseDto responseDto = gatewayService.request(action);
		return responseDto.getMessage();
	}

}
