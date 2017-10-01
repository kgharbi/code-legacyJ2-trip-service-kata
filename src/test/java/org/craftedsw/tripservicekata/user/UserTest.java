package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserTest {


    @Test
    public void isFriendWith_shouldReturnFalse_whenUserHasNoFriends() {

        //given
        User loggedTestedUser = new User();

        //when
        User sam = new User();
        loggedTestedUser.isFriendWith(sam);

        //then
        assertFalse(loggedTestedUser.isFriendWith(sam));
    }

    @Test
    public void isFriendWith_shouldReturnTrue_whenUserOneFriend() {

        //given
        User loggedTestedUser = new User();

        //when
        User sam = new User();
        loggedTestedUser.addFriend(sam);
        loggedTestedUser.isFriendWith(sam);

        //then
        assertTrue(loggedTestedUser.isFriendWith(sam));
    }

    @Test
    public void getFriends_shouldReturnEmpty_whenUserHasNoFriends() {

        //given
        User loggedTestedUser = new User();

        //when
        List<User> friendsList = loggedTestedUser.getFriends();

        //then
        assertThat(friendsList).isEmpty();
    }

    @Test
    public void getFriends_shouldReturnNotEmpty_whenUserHasAtLeastOneFriend() {

        //given
        User loggedTestedUser = new User();
        User sam = new User();

        //when
        loggedTestedUser.addFriend(sam);
        List<User> friendsList = loggedTestedUser.getFriends();

        //then
        assertThat(friendsList).isNotEmpty();
    }

    @Test
    public void getTripList_shouldReturnEmpty_whenUserHasNoTrips() {

        //given
        User loggedTestedUser = new User();

        //when
        List<Trip> tripList = loggedTestedUser.trips();

        //then
        assertThat(tripList).isEmpty();
    }

    @Test
    public void getTripList_shouldReturnNotEmpty_whenUserHasAtLeastOneTrips() {

        //given
        User loggedTestedUser = new User();
        Trip tripToMiami = new Trip();

        //when
        loggedTestedUser.addTrip(tripToMiami);
        List<Trip> tripList = loggedTestedUser.trips();

        //then
        assertThat(tripList).isNotEmpty();
    }

}


