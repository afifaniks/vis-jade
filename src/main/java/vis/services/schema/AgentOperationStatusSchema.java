package vis.services.schema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AgentOperationStatusSchema implements Serializable {

	private Integer status;

	private String message;

	public AgentOperationStatusSchema(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

}
