package vis.services.schema;

import java.io.Serializable;

public class AgentOperationStatusSchema implements Serializable {

	private Integer status;

	private String message;

	public AgentOperationStatusSchema(Integer status, String message) {
		this.status = status;
		this.message = message;
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
