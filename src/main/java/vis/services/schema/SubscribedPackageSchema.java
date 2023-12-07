package vis.services.schema;

import java.io.Serializable;
import java.util.Date;

public class SubscribedPackageSchema extends InsurancePackageSchema implements Serializable {
    String id;

    Date subscribedOn;

    Date claimedOn;

    public SubscribedPackageSchema(String packageId, String packageName, String packageDescription, Double packagePrice, Integer tenure) {
        super(packageId, packageName, packageDescription, packagePrice, tenure);
    }

    public SubscribedPackageSchema(String id, String packageId, String packageName, String packageDescription, Double packagePrice, Integer tenure, Date subscribedOn, Date claimedOn) {
        super(packageId, packageName, packageDescription, packagePrice, tenure);
        this.id = id;
        this.subscribedOn = subscribedOn;
        this.claimedOn = claimedOn;
    }

    public SubscribedPackageSchema() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSubscribedOn() {
        return subscribedOn;
    }

    public void setSubscribedOn(Date subscribedOn) {
        this.subscribedOn = subscribedOn;
    }

    public Date getClaimedOn() {
        return claimedOn;
    }

    public void setClaimedOn(Date claimedOn) {
        this.claimedOn = claimedOn;
    }
}
