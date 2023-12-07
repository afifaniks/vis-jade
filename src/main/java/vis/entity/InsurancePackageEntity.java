package vis.entity;

import jakarta.persistence.*;
import vis.constants.DBTableNames;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = DBTableNames.PACKAGE)
public class InsurancePackageEntity implements DBEntity, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	String packageName;

	String packageDescription;

	Double packagePrice;

	Integer tenure;

	public InsurancePackageEntity(String packageName, String packageDescription, Double packagePrice, Integer tenure) {
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.packagePrice = packagePrice;
		this.tenure = tenure;
	}

	public InsurancePackageEntity() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "InsurancePackageEntity{" + "id=" + id + ", packageName='" + packageName + '\''
				+ ", packageDescription='" + packageDescription + '\'' + ", packagePrice=" + packagePrice + ", tenure="
				+ tenure + '}';
	}

}
