package vis.ontology.predicates;

import jade.content.Concept;
import jade.content.Predicate;

public class SystemError implements Predicate {

	private Integer status;

	private String message;

	public SystemError(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public SystemError() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
