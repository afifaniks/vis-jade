package vis.entity;

import jakarta.persistence.*;
import vis.constants.DBTableNames;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = DBTableNames.SUBSCRIPTION)
public class SubscriptionEntity implements DBEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    String userId;
    String vehicleId;
    String packageId;

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(String userId, String vehicleId, String packageId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.packageId = packageId;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
}
