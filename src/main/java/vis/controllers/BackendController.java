package vis.controllers;

import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
		GatewayResponseDto responseDto = gatewayService.request(req);
		return responseDto.getMessage();
	}

}
