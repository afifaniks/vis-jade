package vis.ontology.concepts;

import jade.content.Concept;

public class Vehicle implements Concept {

	String vehicleId;

	public Vehicle(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Vehicle() {
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

}
