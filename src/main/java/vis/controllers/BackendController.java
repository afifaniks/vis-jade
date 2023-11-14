package vis.controllers;

import jade.wrapper.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vis.dto.GatewayResponseDto;
import vis.services.AgentGatewayService;

@RestController
public class BackendController {
    private final AgentGatewayService gatewayService = new AgentGatewayService();

    public BackendController() throws ControllerException, InterruptedException {
    }

    @GetMapping("/api")
    public String test() throws ControllerException, InterruptedException {
        System.out.println("askhdkajsd");
        GatewayResponseDto responseDto = gatewayService.request("test");
        return responseDto.getMessage();
    }
}
