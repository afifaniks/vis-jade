package vis.dto;

public class GatewayResponseDto {

	int statusCode;

	String message;

	public GatewayResponseDto() {
	}

	public GatewayResponseDto(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
