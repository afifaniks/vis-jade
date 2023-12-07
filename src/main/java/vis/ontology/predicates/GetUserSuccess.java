package vis.ontology.predicates;

import jade.content.Predicate;
import vis.services.schema.UserProfileSchema;

public class GetUserSuccess implements Predicate {

	private UserProfileSchema user;

	public GetUserSuccess() {
	}

	public GetUserSuccess(UserProfileSchema user) {
		this.user = user;
	}

	public UserProfileSchema getUser() {
		return user;
	}

	public void setUser(UserProfileSchema user) {
		this.user = user;
	}

}
