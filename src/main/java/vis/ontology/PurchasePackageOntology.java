package vis.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;

public class PurchasePackageOntology extends Ontology {
    public static final String ONTOLOGY_NAME = "Purchase-Package-Ontology";
    public static final String PACKAGE = "Package";
    public static final String PACKAGE_NAME = "packageName";
    public static final String PACKAGE_PRICE = "packagePrice";
    public static final String TENURE = "tenure";
    public static final String SUBSCRIBE = "Subscribe";

    private static Ontology theInstance = new PurchasePackageOntology();

    public static Ontology getInstance() {
        return theInstance;
    }

    // Private constructor
    private PurchasePackageOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        // TODO: TBD
    }
}
