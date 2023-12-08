package vis.ontology.actions;

import jade.content.AgentAction;
import vis.ontology.concepts.Subscription;
import vis.ontology.concepts.User;

public class ClaimInsurance implements AgentAction {

    private User user;

    private Subscription subscription;

    public ClaimInsurance(User user, Subscription subscription) {
        this.user = user;
        this.subscription = subscription;
    }

    public ClaimInsurance() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

}
