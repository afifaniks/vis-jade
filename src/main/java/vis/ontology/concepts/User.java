package vis.ontology.concepts;

import jade.content.Concept;

public class User implements Concept {

	String userId;

	public User(String userId) {
		this.userId = userId;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
