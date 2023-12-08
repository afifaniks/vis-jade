package vis.entity;

import jakarta.persistence.*;
import vis.constants.DBTableNames;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DBTableNames.SUBSCRIPTION)
public class SubscriptionEntity implements DBEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    String userEmail;

    String vehicleId;

    String packageId;

    Date subscribedOn;

    Date claimedOn;

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(String userEmail, String vehicleId, String packageId, Date subscribedOn) {
        this.userEmail = userEmail;
        this.vehicleId = vehicleId;
        this.packageId = packageId;
        this.subscribedOn = subscribedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userId) {
        this.userEmail = userId;
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

    public Date getClaimedOn() {
        return claimedOn;
    }

    public void setClaimedOn(Date claimedOn) {
        this.claimedOn = claimedOn;
    }

    public Date getSubscribedOn() {
        return subscribedOn;
    }

    public void setSubscribedOn(Date subscribedOn) {
        this.subscribedOn = subscribedOn;
    }

}
