package vis.ontology.predicates;

import jade.content.Concept;

public class Vehicle implements Concept {

	String vehicleId;

	String userId;

	public Vehicle(String vehicleId, String userId) {
		this.vehicleId = vehicleId;
		this.userId = userId;
	}

	public Vehicle() {
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
