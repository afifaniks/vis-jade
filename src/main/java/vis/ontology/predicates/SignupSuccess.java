package vis.ontology.predicates;

import jade.content.Predicate;

public class SignupSuccess implements Predicate {

	private int status;

	private String message;

	public SignupSuccess(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public SignupSuccess() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
