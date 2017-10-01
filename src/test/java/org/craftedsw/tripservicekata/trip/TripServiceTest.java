package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {

    private User loggedUser;

    @Test(expected = UserNotLoggedInException.class)  //permet de dire à junit.Test de le considérer comme un Test et de l'executer
    public void getTripByUser_shouldThrowException_whenUserNotLogged() {

        //given
        TripService tested = new TestableTripService();

        //when
        tested.getTripsByUser(new User());//on execute la version qu'on a ici
                                          // et non celle de la class TripService
        this.loggedUser = null;

        //then
        //pas besoin il est en expected en haut car c'est du try catch. Si
        //on fait avec try catch y en aurait pour plus de 5 lignes.
    }

    @Test
    public void getTripByUser_shouldReturnNoTrips_whenUserHasNoFriends() {

        //given
        TripService tested = new TestableTripService();
        this.loggedUser = new User();
        User user = new User();

        //when
        tested.getTripsByUser(new User());
        List<Trip> tripList = tested.getTripsByUser(user);

        //then
        assertThat(tripList).isEmpty();
    }

    @Test
    public void getTripByUser_shouldReturnTrips_whenUserAreFriends() {

        //given
        TripService tested = new TestableTripService();
        User paul = new User();
        User mary = new User();

        paul.addFriend(mary);
        this.loggedUser = mary;

        //when
        List<Trip> trips = tested.getTripsByUser(paul);

        //then
        assertThat(trips).isNotEmpty();

    }

    @Test
    public void getTripByUser_shouldReturnEmptyTrips_whenUserAreNotFriends() {

        //given
        TripService tested = new TestableTripService();
        User jim = new User();
        User jane = new User();
        User sam = new User();

        jim.addFriend(sam);
        this.loggedUser = jane;

        //when
        List<Trip> trips = tested.getTripsByUser(jim);

        //then
        assertThat(trips).isEmpty();

    }


    //temporaire. C'est de la surcharge.
    class TestableTripService extends TripService{
        protected User getLoggedUser() {
            return loggedUser;
        }

        protected List<Trip> tripsByUser(User user) {
            List<Trip> trips = new ArrayList<Trip>();
            trips.add(new Trip());

            return trips;
        }

    }
}

