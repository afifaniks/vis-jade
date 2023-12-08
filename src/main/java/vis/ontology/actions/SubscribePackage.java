package vis.ontology.actions;

import jade.content.AgentAction;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.User;
import vis.ontology.concepts.Vehicle;

public class SubscribePackage implements AgentAction {

    private InsurancePackage insurancePackage;

    private User user;

    private Vehicle vehicle;

    public SubscribePackage(InsurancePackage insurancePackage, User user, Vehicle vehicle) {
        this.insurancePackage = insurancePackage;
        this.user = user;
        this.vehicle = vehicle;
    }

    public SubscribePackage() {
    }

    public InsurancePackage getInsurancePackage() {
        return insurancePackage;
    }

    public void setInsurancePackage(InsurancePackage insurancePackage) {
        this.insurancePackage = insurancePackage;
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
