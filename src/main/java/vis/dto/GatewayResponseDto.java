package vis.dto;

public class GatewayResponseDto {

	String jsonContent;

	public GatewayResponseDto(String jsonContent) {
		this.jsonContent = jsonContent;
	}

	public GatewayResponseDto() {
	}

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}

}
