package vis.services;

import jade.core.AID;
import jade.core.Profile;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.List;
import jade.util.leap.Properties;
import jade.wrapper.ControllerException;
import jade.wrapper.gateway.JadeGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.dto.GatewayResponseDto;

public class AgentGatewayService {
    private String mainContainerHostName = "localhost";
    private String mainContainerPort = "1099";
    private MainContainerAgentsRetrieverService retrieverService;
    private final Logger logger = LoggerFactory.getLogger(AgentGatewayService.class);
    private final AID adminAgent;

    public AgentGatewayService() throws ControllerException, InterruptedException {
        retrieverService = new MainContainerAgentsRetrieverService();
        Properties mainContainerProps = new Properties();
        mainContainerProps.setProperty(Profile.MAIN_HOST, mainContainerHostName);
        mainContainerProps.setProperty(Profile.MAIN_PORT, mainContainerPort);
        JadeGateway.init(null, mainContainerProps);
        adminAgent = getAdminAgent();
    }

    private AID getAdminAgent() throws ControllerException, InterruptedException {
        JadeGateway.execute(retrieverService);
        List agents = retrieverService.getAgents();
        if (agents == null || agents.isEmpty()) {
            logger.error("No agent is running.");
            return null;
        }

        for (int i = 0; i < agents.size(); ++i) {
            AID agent = (AID) agents.get(i);
            if (agent.getLocalName().equals("Admin")) {
                return agent;
            }
        }
        logger.error("Admin agent is not running.");
        return null;
    }


    public GatewayResponseDto request(String req) {
        final String[] adminResponse = new String[1];
        try {
            if (adminAgent != null) {
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                message.addReceiver(new AID(adminAgent.getLocalName(), AID.ISLOCALNAME));
                message.setContent(req);
                JadeGateway.execute(new OneShotBehaviour() {
                    @Override
                    public void action() {
                        ACLMessage response = null;
                        myAgent.send(message);
                        response = myAgent.blockingReceive();
                        adminResponse[0] = response.getContent();
                        logger.debug("Response from Admin agent: " + response);
                    }
                    });
                return new GatewayResponseDto(200, adminResponse[0]);
            }
            else {
                logger.error("Admin agent is not found.");
            }
        }
        catch (Exception e) {
            logger.error(e.toString());
        }

        return new GatewayResponseDto();
    }
}
