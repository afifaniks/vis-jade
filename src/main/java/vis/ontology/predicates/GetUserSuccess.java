package vis.ontology.predicates;

import jade.content.Predicate;
import vis.ontology.concepts.User;

public class GetUserSuccess implements Predicate {
    private User user;

    public GetUserSuccess() {
    }

    public GetUserSuccess(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
