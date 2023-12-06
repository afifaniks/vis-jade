package vis.ontology.concepts;

import jade.content.Concept;

public class InsurancePackage implements Concept {

	private String packageId;

	String packageName;

	String packageDescription;

	Double packagePrice;

	Integer tenure;

	public InsurancePackage(String packageId, String packageName, String packageDescription, Double packagePrice,
			Integer tenure) {
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.packagePrice = packagePrice;
		this.tenure = tenure;
	}

	public InsurancePackage(String packageId) {
		this.packageId = packageId;
	}

	public InsurancePackage() {
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
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

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

}
