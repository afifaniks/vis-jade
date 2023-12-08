package vis.agents;

import java.io.Serializable;

public class AgentActionIdentifier implements Serializable {

    public String targetAgent;

    public String action;

    public String contents;

    public AgentActionIdentifier(String targetAgent, String action, String contents) {
        this.targetAgent = targetAgent;
        this.action = action;
        this.contents = contents;
    }

    public String getTargetAgent() {
        return targetAgent;
    }

    public void setTargetAgent(String targetAgent) {
        this.targetAgent = targetAgent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
