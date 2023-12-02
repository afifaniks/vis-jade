package vis.ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;

public class PackageSubscriptionOntology extends BeanOntology {

	private static PackageSubscriptionOntology instance = new PackageSubscriptionOntology();

	private PackageSubscriptionOntology() {
		super(Ontologies.PACKAGE_SUBSCRIPTION_ONTO);
		try {
			add("vis.ontology.actions");
			add("vis.ontology.concepts");
		}
		catch (BeanOntologyException e) {
			e.printStackTrace();
		}
	}

	public static Ontology getInstance() {
		return instance;
	}

}
