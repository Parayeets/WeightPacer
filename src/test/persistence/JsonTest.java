package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDailyRecord(DailyRecord dailyRecord, Double mass) {
        assertEquals(dailyRecord.getNewMass(), mass);

    }
}
