package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDailyRecord(DailyRecord dailyRecord, Double mass) {
        assertEquals(dailyRecord.getNewMass(), mass);

    }

}
