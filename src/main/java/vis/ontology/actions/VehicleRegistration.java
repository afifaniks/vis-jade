package vis.ontology.actions;

import jade.content.onto.basic.Action;
import vis.ontology.concepts.User;
import vis.ontology.concepts.Vehicle;

public class VehicleRegistration extends Action {

    private User user;
    private Vehicle vehicle;

    public VehicleRegistration(User user, Vehicle vehicle) {
        this.user = user;
        this.vehicle = vehicle;
    }

    public VehicleRegistration() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
