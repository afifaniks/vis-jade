package vis.ontology.concepts;

import jade.content.Concept;

public class InsurancePackage implements Concept {

	private String packageId;

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

}
