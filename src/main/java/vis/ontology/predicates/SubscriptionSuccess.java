package vis.ontology.predicates;

import jade.content.Predicate;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.User;

public class SubscriptionSuccess implements Predicate {

    private InsurancePackage insurancePackage;

    private User user;

    public SubscriptionSuccess(InsurancePackage insurancePackage, User user) {
        this.insurancePackage = insurancePackage;
        this.user = user;
    }

    public SubscriptionSuccess() {
    }

    public InsurancePackage getInsurancePackage() {
        return insurancePackage;
    }

    public void setInsurancePackage(InsurancePackage insurancePackage) {
        this.insurancePackage = insurancePackage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
