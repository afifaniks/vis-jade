package vis.dto.response;

public class PackageRecommendationResponse {

	String packageName;

	String packageDescription;

	Double packagePrice;

	Integer tenure;

	public PackageRecommendationResponse(String packageName, String packageDescription, Double packagePrice,
			Integer tenure) {
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.packagePrice = packagePrice;
		this.tenure = tenure;
	}

	public PackageRecommendationResponse(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(Double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

}
