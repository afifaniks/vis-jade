package vis.ontology.predicates;

import jade.content.Predicate;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.User;

import java.util.ArrayList;

public class Recommendation implements Predicate {

    private User user;

    private ArrayList<InsurancePackage> packages;

    public Recommendation(User user, ArrayList<InsurancePackage> packages) {
        this.user = user;
        this.packages = packages;
    }

    public Recommendation() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<InsurancePackage> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<InsurancePackage> packages) {
        this.packages = packages;
    }

}
