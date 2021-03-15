package model;

import model.DailyRecord;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DailyRecordTest {

    private DailyRecord testDailyRecordOne;
    private DailyRecord testDailyRecordTwo;
    private DailyRecord testDailyRecordThree;
    private DailyRecord testDailyRecordFour;
    private User testUser;
    private User testUserTwo;
    private User testUserThree;

    @BeforeEach
    void runBefore() {
        testDailyRecordOne = new DailyRecord(200.0);
        testDailyRecordTwo = new DailyRecord(105.0);
        testDailyRecordThree = new DailyRecord(162.3);
        testDailyRecordFour = new DailyRecord(223.4);

        testUser = new User("Samantha", 165.0, 145.0);
        testUserTwo = new User("Navid", 210.0, 177.5);
        testUserThree = new User("Rohan", 294.1, 200.0);

    }

    @Test
    void testConstructor() {
        assertEquals(200.0, testDailyRecordOne.getNewMass());
        assertEquals(105.0, testDailyRecordTwo.getNewMass());
        assertEquals(162.3, testDailyRecordThree.getNewMass());
        assertEquals(223.4, testDailyRecordFour.getNewMass());

    }

    // Test to see that the users have not yet reached their final desired mass.
    @Test
    void testNotYetReachedGoal() {
        assertFalse(testDailyRecordFour.reachedGoal(testUserThree));
        assertFalse(testDailyRecordThree.reachedGoal(testUser));
    }

    // Test to see that the users have exactly reached their final desired mass.
    @Test
    void testReachedExactGoal() {
        assertTrue(testDailyRecordOne.reachedGoal(testUserThree));

    }

    // Test to see that the users EXCEEDED their final desired mass.
    @Test
    void testExceededExactGoal() {
        assertTrue(testDailyRecordTwo.reachedGoal(testUserTwo));

    }

    // Test to see that a user is chipping away towards their final desired mass.
    @Test
    void testOneCurrentTrajectoryTowardsGoal() {
        assertEquals(Math.round(23 / 0.285142857), testDailyRecordFour.currentTrajectoryTowardsGoal(testUserThree));

    }

    // Test to see that a user is slightly further away from their final desired mass.
    @Test
    void testTwoCurrentTrajectoryTowardsGoal() {
        assertEquals(Math.round(55 / 0.285142857), testDailyRecordOne.currentTrajectoryTowardsGoal(testUser));

    }

    @Test
    void testToJson() {
        JSONObject json = testDailyRecordOne.toJson();
        //JSONObject jsonTwo = testUserTwo.toJson();
        assertEquals(json.get("new mass"), testDailyRecordOne.getNewMass());
        //assertEquals(jsonTwo.get("current trajectory"), testDailyRecordOne.currentTrajectoryTowardsGoal(testUserTwo));


    }

}