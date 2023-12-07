package vis.agents.behaviour;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.ontology.VISOntology;
import vis.ontology.actions.GetUser;
import vis.ontology.actions.PackageRecommendation;
import vis.ontology.actions.SubscribePackage;
import vis.ontology.actions.VehicleRegistration;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.predicates.*;
import vis.services.CustomerAssistantService;
import vis.services.CustomerAssistantServiceImpl;
import vis.services.schema.*;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerAssistantBehaviour extends CyclicBehaviour {

	private final CustomerAssistantService customerAssistantService;

	private final Codec codec = new SLCodec();

	private final Ontology ontology = VISOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(ontology.getName()));

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	public CustomerAssistantBehaviour(Agent agent) {
		super(agent);
		customerAssistantService = new CustomerAssistantServiceImpl(agent);
	}

	@Override
	public void action() {
		ACLMessage receivedMessage = null;

		myAgent.getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL);
		myAgent.getContentManager().registerOntology(ontology);
		receivedMessage = myAgent.blockingReceive(messageTemplate);

		logger.info("Request received from admin agent");

		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(receivedMessage.getSender());
		responseMessage.setLanguage(codec.getName());
		responseMessage.setOntology(ontology.getName());

		try {
			if (receivedMessage.getPerformative() == ACLMessage.REQUEST) {

				Action actionContent = (Action) myAgent.getContentManager().extractContent(receivedMessage);

				if (actionContent.getAction() instanceof SubscribePackage) {
					subscribePackage(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof PackageRecommendation) {
					recommendPackages(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof VehicleRegistration) {
					registerVehicle(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof GetUser) {
					getUser(actionContent, responseMessage);
				}
			}
		}
		catch (Exception e) {
			logger.error("Error occurred while subscribing package. Sending error response...");
			e.printStackTrace();
		}
	}

	private void subscribePackage(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		SubscribePackage subscribePackage = (SubscribePackage) actionContent.getAction();
		SubscriptionRequestSchema subscriptionRequestSchemaRequest = new SubscriptionRequestSchema(
				subscribePackage.getUser().getUserId(), subscribePackage.getVehicle().getVehicleId(),
				subscribePackage.getInsurancePackage().getId());

		SubscriptionStatusSchema subscriptionStatus = customerAssistantService
			.subscribePackage(subscriptionRequestSchemaRequest);

		if (subscriptionStatus.getStatus() == 200) {
			myAgent.getContentManager()
				.fillContent(responseMessage,
						new SubscriptionSuccess(subscribePackage.getInsurancePackage(), subscribePackage.getUser()));
			logger.info("Package subscription successful");
		}
		else {
			SystemError systemError = new SystemError(500, "Subscription error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not subscribe package");
		}

		myAgent.send(responseMessage);
	}

	private void recommendPackages(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		PackageRecommendation packageRecommendationAction = (PackageRecommendation) actionContent.getAction();
		RecommendationRequestSchema recommendationRequestSchema = new RecommendationRequestSchema(
				packageRecommendationAction.getUser().getEmail(),
				packageRecommendationAction.getVehicle().getVehicleId());

		ArrayList<InsurancePackageSchema> recommendations = customerAssistantService
			.getPackageRecommendation(recommendationRequestSchema);

		if (recommendations != null && !recommendations.isEmpty()) {
			ArrayList<InsurancePackage> packageConcepts = new ArrayList<>();

			for (InsurancePackageSchema pkg : recommendations) {
				packageConcepts.add(new InsurancePackage(pkg.getPackageId(), pkg.getPackageName(),
						pkg.getPackageDescription(), pkg.getPackagePrice(), pkg.getTenure()));
			}
			myAgent.getContentManager()
				.fillContent(responseMessage,
						new Recommendation(packageRecommendationAction.getUser(), packageConcepts));
			logger.info("Packages recommended.");
		}
		else {
			SystemError systemError = new SystemError(500, "Internal error occurred while generating recommendation.");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
		}

		myAgent.send(responseMessage);
	}

	private void registerVehicle(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		VehicleRegistration vehicleRegistration = (VehicleRegistration) actionContent.getAction();
		VehicleRegistrationSchema vehicleRegistrationSchema = new VehicleRegistrationSchema(
				vehicleRegistration.getUser().getEmail(), vehicleRegistration.getVehicle().getVehicleName(),
				vehicleRegistration.getVehicle().getVehicleModel(), vehicleRegistration.getVehicle().getVehicleType(),
				vehicleRegistration.getVehicle().getLicenseNumber(),
				vehicleRegistration.getVehicle().getVehicleRegistrationNumber(),
				vehicleRegistration.getVehicle().getPurchaseDate(), vehicleRegistration.getVehicle().getVehicleStatus(),
				vehicleRegistration.getVehicle().getMileage());

		VehicleRegistrationStatusSchema vehicleRegistrationStatusSchema = customerAssistantService
			.registerVehicle(vehicleRegistrationSchema);

		if (vehicleRegistrationStatusSchema.getStatus() == 200) {
			myAgent.getContentManager()
				.fillContent(responseMessage, new VehicleRegistrationSuccess(vehicleRegistration.getUser().getUserId(),
						vehicleRegistration.getVehicle().getVehicleId()));
			logger.info("Vehicle Registration successful");
		}
		else {
			SystemError systemError = new SystemError(500, "Vehicle Registration error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not register vehicle");
		}

		myAgent.send(responseMessage);
	}

	private void getUser(Action actionContent, ACLMessage responseMessage)
			throws OntologyException, Codec.CodecException, UnreadableException, IOException {
		GetUser getUser = (GetUser) actionContent.getAction();
		GetUserRequestSchema getUserRequestSchema = new GetUserRequestSchema(getUser.getUser().getEmail());

		UserProfileSchema userProfileSchema = customerAssistantService.getUser(getUserRequestSchema);

		if (userProfileSchema != null) {
			myAgent.getContentManager().fillContent(responseMessage, new GetUserSuccess(userProfileSchema));
			logger.info("User Retrieved: " + getUser.getUser());
		}
		else {
			SystemError systemError = new SystemError(500, "User Retrieval error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not retrieve the user");
		}

		myAgent.send(responseMessage);
	}

}
