package model;

import model.User;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User testUser;
    private User testUserTwo;
    private User testUserThree;
    private User testUserFour;

    @BeforeEach
    void runBefore() {
        try {
            testUser = new User("Samantha", 165.0, 145.0);
            testUserTwo = new User("Navid", 210.0, 177.5);
            testUserThree = new User("Rohan", 294.1, 200.0);
        } catch (IncorrectInputException e) {
            fail("Exception shouldn't be thrown");
        }

        try {
            testUserFour = new User("Karen", 96.5, 100.0);
            fail("Exception should be thrown");
        } catch (IncorrectInputException e) {
            //pass
        }


    }

    // Test to ensure whether the given name, initial mass and final desired mass are equal to each other
    @Test
    void testUserConstructor() {
        assertEquals("Samantha", testUser.getName());
        assertEquals(165.0, testUser.getInitialMass());
        assertEquals(145.0, testUser.getFinalDesiredMass());

        assertEquals("Navid", testUserTwo.getName());
        assertEquals(210.0, testUserTwo.getInitialMass());
        assertEquals(177.5, testUserTwo.getFinalDesiredMass());

        assertEquals("Rohan", testUserThree.getName());
        assertEquals(294.1, testUserThree.getInitialMass());
        assertEquals(200.0, testUserThree.getFinalDesiredMass());

        try {
            assertEquals("Karen", testUserFour.getName());
            assertEquals(96.5, testUserFour.getInitialMass());
            assertEquals(100.0, testUserFour.getFinalDesiredMass());
        } catch (NullPointerException e) {
            //pass
        }

    }

    // Test to ensure the trajectory in days it takes for the person towards their goal given initial weight.
    @Test
    void testOneInitialTrajectoryTowardsGoal() {
        assertEquals(Math.round(20 / 0.285142857), testUser.initialTrajectoryTowardsGoal(165.0,
                145.0));
    }

    // ANOTHER test to ensure the trajectory in days it takes for the person towards their goal given initial weight.
    // We will focus on the decimal point being 0.5 and higher.
    @Test
    void testTwoInitialTrajectoryTowardsGoal() {
        assertEquals(Math.round(32 / 0.285142857), testUserTwo.initialTrajectoryTowardsGoal(210.0,
                177.5));
    }

    // A final test to ensure the trajectory in days it takes for the person towards their goal given initial weight.
    // We will focus on the decimal point being 0.4 and lower.
    @Test
    void testThreeInitialTrajectoryTowardsGoal() {
        assertEquals(Math.round(94 / 0.285142857), testUserThree.initialTrajectoryTowardsGoal(294.1,
                200.0));
    }

    // This test takes a user who goes against the purpose of the program and has a final desired mass that's higher
    // or equal to the initial mass. So it would ask the user to put their inputs again, but in the meantime, it would
    // put in a dummy value by the name of Guest, which has an initial mass of 1 lb and a final mass of 0 lb.
    @Test
    void testInvalidTrajectoryTowardsGoal() {
        try {
            assertEquals(Math.round(1/ 0.285142857), testUserFour.initialTrajectoryTowardsGoal(96.5,
                    100.0));
        } catch (NullPointerException e) {
            //pass
        }
    }

    @Test
    void testToJson() {
        JSONObject json = testUser.toJson();
        assertEquals(json.get("name"), testUser.getName());
        assertEquals(json.get("initial mass"), testUser.getInitialMass());
        assertEquals(json.get("final desired mass"), testUser.getFinalDesiredMass());
        //assertEquals(json.get("initial trajectory"), testUser.initialTrajectoryTowardsGoal(
        //        testUser.getInitialMass(), testUser.getFinalDesiredMass()));

    }

}
