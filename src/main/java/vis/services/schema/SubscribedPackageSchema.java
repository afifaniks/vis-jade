package vis.services.schema;

import java.io.Serializable;
import java.util.Date;

public class SubscribedPackageSchema extends InsurancePackageSchema implements Serializable {
    Date subscribedOn;

    Date claimedOn;

    public SubscribedPackageSchema(String packageId, String packageName, String packageDescription, Double packagePrice, Integer tenure) {
        super(packageId, packageName, packageDescription, packagePrice, tenure);
    }

    public SubscribedPackageSchema(String packageId, String packageName, String packageDescription, Double packagePrice, Integer tenure, Date subscribedOn, Date claimedOn) {
        super(packageId, packageName, packageDescription, packagePrice, tenure);
        this.subscribedOn = subscribedOn;
        this.claimedOn = claimedOn;
    }

    public SubscribedPackageSchema() {
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
