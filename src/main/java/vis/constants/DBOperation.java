package vis.constants;

import java.io.Serializable;

/***
 * This class is used to represent the different operations that can be performed on the
 * database.
 */
public class DBOperation implements Serializable {

	public enum Operation {

		LOGIN, SIGNUP, GET_PACKAGES, SUBSCRIBE, CLAIM, REGISTER_VEHICLE, GET_USER

	}

	Operation operation;

	Object additionalObject;

	public DBOperation(Operation operation, Object additionalObject) {
		this.operation = operation;
		this.additionalObject = additionalObject;
	}

	public DBOperation(Operation operation) {
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Object getAdditionalObject() {
		return additionalObject;
	}

	public void setAdditionalObject(Object additionalObject) {
		this.additionalObject = additionalObject;
	}

}
