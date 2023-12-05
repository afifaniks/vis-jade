package vis.ontology.actions;

import jade.content.onto.basic.Action;

public class VehicleRegistration extends Action {

    private int status;

    private String message;

    public VehicleRegistration(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public VehicleRegistration() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
