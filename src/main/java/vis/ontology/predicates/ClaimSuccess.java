package vis.ontology.predicates;

import jade.content.Predicate;
import vis.ontology.concepts.User;

public class ClaimSuccess implements Predicate {

    private User user;

    public ClaimSuccess(User user) {
        this.user = user;
    }

    public ClaimSuccess() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
