package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDailyRecord(DailyRecord dailyRecord, Double mass, int trajectory, User user) {
        assertEquals(dailyRecord.currentTrajectoryTowardsGoal(user), trajectory);
        assertEquals(dailyRecord.getNewMass(), mass);

    }
}
