package vis.ontology.concepts;

import jade.content.Concept;

public class Subscription implements Concept {

    String subscriptionId;

    public Subscription(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Subscription() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

}
