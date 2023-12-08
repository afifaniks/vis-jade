package vis.dto;

public class InsurancePackageDto {

    String packageId;

    String packageName;

    Double packagePrice;

    Integer tenureInMonths;

    public InsurancePackageDto(String packageId, String packageName, Double packagePrice, Integer tenureInMonths) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.tenureInMonths = tenureInMonths;
    }

    public InsurancePackageDto() {
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

    public Integer getTenureInMonths() {
        return tenureInMonths;
    }

    public void setTenureInMonths(Integer tenureInMonths) {
        this.tenureInMonths = tenureInMonths;
    }

}
