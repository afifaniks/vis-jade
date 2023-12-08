package vis.ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;

/***
 * This class is used to create the ontology.
 */
public class VISOntology extends BeanOntology {

    private static VISOntology instance = new VISOntology();

    private VISOntology() {
        super(OntologyNames.VIS_ONTOLOGY);
        try {
            add("vis.ontology.actions");
            add("vis.ontology.concepts");
            add("vis.ontology.predicates");
        } catch (BeanOntologyException e) {
            e.printStackTrace();
        }
    }

    public static Ontology getInstance() {
        return instance;
    }

}
