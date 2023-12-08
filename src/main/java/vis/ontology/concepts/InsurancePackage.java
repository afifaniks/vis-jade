package vis.ontology.concepts;

import jade.content.Concept;

public class InsurancePackage implements Concept {

    private String id;

    String packageName;

    String packageDescription;

    Double packagePrice;

    Integer tenure;

    public InsurancePackage(String id, String packageName, String packageDescription, Double packagePrice, Integer tenure) {
        this.id = id;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.packagePrice = packagePrice;
        this.tenure = tenure;
    }

    public InsurancePackage(String id) {
        this.id = id;
    }

    public InsurancePackage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
