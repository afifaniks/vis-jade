package vis.services.schema;

import java.io.Serializable;

public class SignupStatusSchema extends AgentOperationStatusSchema implements Serializable {

	public SignupStatusSchema(Integer status, String message) {
		super(status, message);
	}

}
