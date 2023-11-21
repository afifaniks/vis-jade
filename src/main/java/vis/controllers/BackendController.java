package vis.controllers;

import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vis.agents.AgentType;
import vis.dto.AgentAction;
import vis.dto.GatewayResponseDto;
import vis.services.AgentGatewayService;

@RestController
public class BackendController {

	private final AgentGatewayService gatewayService;

	@Autowired
	public BackendController(AgentGatewayService gatewayService) throws ControllerException, InterruptedException {
		this.gatewayService = gatewayService;
	}

	@GetMapping("/api")
	public String test(@RequestParam String req) throws ControllerException, InterruptedException {
		AgentAction action = new AgentAction(
				AgentType.AUTHENTICATION,
				"login",
				"username;password"
		);
		GatewayResponseDto responseDto = gatewayService.request(action);
		return responseDto.getMessage();
	}

}
