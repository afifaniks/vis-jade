package vis.ontology.concepts;

import jade.content.Concept;

public class User implements Concept {

	String userId;

	String userName;

	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
