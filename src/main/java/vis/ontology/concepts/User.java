package vis.ontology.concepts;

import jade.content.Concept;

public class User implements Concept {

	String userId;

	String email;

	public User(String userId) {
		this.userId = userId;
	}

	public User(String userId, String email) {
		this.userId = userId;
		this.email = email;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
