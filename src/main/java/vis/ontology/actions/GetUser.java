package vis.ontology.actions;

import jade.content.AgentAction;
import vis.ontology.concepts.User;

public class GetUser implements AgentAction {

	private User user;

	public GetUser(User user) {
		this.user = user;
	}

	public GetUser() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
