package vis.util;

import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.AgentContainer;
import jade.core.ContainerID;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.QueryAgentsOnLocation;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.util.leap.List;
import org.springframework.stereotype.Component;

import java.util.Vector;

/***
 * The MainContainerAgentsRetriever retrieves the list of agents running in the main
 * container.
 */
@Component
public class MainContainerAgentsRetriever extends AchieveREInitiator {

	private List agents;

	public MainContainerAgentsRetriever() {
		super(null, null);
	}

	public List getAgents() {
		return agents;
	}

	public void onStart() {
		super.onStart();

		// Be sure the JADEManagementOntology and the Codec for the SL language are
		// registered in the Gateway Agent
		myAgent.getContentManager().registerLanguage(new SLCodec());
		myAgent.getContentManager().registerOntology(JADEManagementOntology.getInstance());
	}

	/***
	 * Prepare the request to be sent to the AMS.
	 * @param initialMsg The initial ACLMessage to be sent to the AMS.
	 * @return A vector containing the ACLMessage to be sent to the AMS.
	 */
	@Override
	protected Vector prepareRequests(ACLMessage initialMsg) {
		Vector v = null;

		// Prepare the request to be sent to the AMS
		ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(myAgent.getAMS());
		request.setOntology(JADEManagementOntology.getInstance().getName());
		request.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

		QueryAgentsOnLocation qaol = new QueryAgentsOnLocation();
		qaol.setLocation(new ContainerID(AgentContainer.MAIN_CONTAINER_NAME, null));
		Action actExpr = new Action(myAgent.getAMS(), qaol);
		try {
			myAgent.getContentManager().fillContent(request, actExpr);
			v = new Vector(1);
			v.add(request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/***
	 * Handle the response from the AMS.
	 * @param inform The ACLMessage received from the AMS.
	 */
	@Override
	protected void handleInform(ACLMessage inform) {
		try {
			// Get the result from the AMS, parse it and store the list of agents
			Result result = (Result) myAgent.getContentManager().extractContent(inform);
			agents = (List) result.getValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}