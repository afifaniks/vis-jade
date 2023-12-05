package vis.ontology.predicates;
import jade.content.Predicate;

public class VehicleRegistrationSuccess implements Predicate{
    String userId;
    String vehicleId;

    public VehicleRegistrationSuccess(String userId, String vehicleId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
    }

    public VehicleRegistrationSuccess() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    @Override
    public String toString() {
        return "VehicleRegistrationSuccess [userId=" + userId + ", vehicleId=" + vehicleId + "]";
    }

}
