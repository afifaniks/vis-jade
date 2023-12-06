package vis.dto.response;

public class VehicleRegistrationResponse {

	Integer statusCode;

	String statusMessage;

	public VehicleRegistrationResponse(Integer statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public VehicleRegistrationResponse(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
