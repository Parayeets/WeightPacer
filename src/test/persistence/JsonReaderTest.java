package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    User testUser;

    @BeforeEach
    void runBefore() {
        testUser = new User("Navid", 200.00, 100.00);

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Records r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecords() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecords.json");
        try {
            Records r = reader.read();
            assertEquals(0, r.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderRecords() {
        JsonReader reader = new JsonReader("./data/testReaderRecords.json");
        try {
            Records r = reader.read();
            List<DailyRecord> dailyRecords = r.getRecordsList();
            assertEquals(2, dailyRecords.size());
            checkDailyRecord(dailyRecords.get(0), 190.00);
            checkDailyRecord(dailyRecords.get(1), 180.00);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
