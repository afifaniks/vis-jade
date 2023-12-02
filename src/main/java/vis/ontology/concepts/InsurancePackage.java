package vis.ontology.concepts;

import jade.content.Concept;

public class InsurancePackage implements Concept {

	private String packageId;

	private String packageName;

	public InsurancePackage(String packageId, String packageName) {
		this.packageId = packageId;
		this.packageName = packageName;
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

}
