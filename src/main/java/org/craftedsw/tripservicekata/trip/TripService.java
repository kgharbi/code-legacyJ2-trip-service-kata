package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();

		validate(loggedUser);

		if (user.isFriendWith(loggedUser)) {
			return tripsByUser(user);
		}

		return noTrips();
	}

	protected void validate(User loggedUser){
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected ArrayList<Trip> noTrips(){
		return new ArrayList<Trip>();
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

	protected List<Trip> tripsByUser(User user){
		return TripDAO.findTripsByUser(user);
	}

}
