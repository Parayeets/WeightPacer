package model;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User testUser;
    private User testUserTwo;
    private User testUserThree;

    @BeforeEach
    void runBefore() {
        testUser = new User("Samantha", 165.0, 145.0);
        testUserTwo = new User("Navid", 210.0, 177.5);
        testUserThree = new User("Rohan", 294.1, 200.0);
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
}
