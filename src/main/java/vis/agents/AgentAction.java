package vis.agents;

import java.io.Serializable;

public class AgentAction implements Serializable {

	public AgentType targetAgent;

	public String action;

	public String contents;

	public AgentAction(AgentType targetAgent, String action, String contents) {
		this.targetAgent = targetAgent;
		this.action = action;
		this.contents = contents;
	}

	public AgentType getTargetAgent() {
		return targetAgent;
	}

	public void setTargetAgent(AgentType targetAgent) {
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
