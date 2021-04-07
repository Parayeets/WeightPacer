package persistence;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    private User testUser;

    @BeforeEach
    void runBefore() throws IncorrectInputException {
        testUser = new User("Navid", 200.00, 100.00);

    }


    @Test
    void testWriterInvalidFile() {
        try {
            Records r = new Records(testUser);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecords() {
        try {
            Records r = new Records(testUser);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecords.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecords.json");
            r = reader.read();
            assertEquals(r.getSize(), 0);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (IncorrectInputException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRecords() {
        try {
            Records r = new Records(testUser);
            r.addDailyRecord(new DailyRecord(190.00));
            r.addDailyRecord(new DailyRecord(180.00));
            JsonWriter writer = new JsonWriter("./data/testWriterRecords.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRecords.json");
            r = reader.read();
            List<DailyRecord> dailyRecords = r.getRecordsList();
            assertEquals(2, dailyRecords.size());
            checkDailyRecord(dailyRecords.get(0), 190.00);
            checkDailyRecord(dailyRecords.get(1), 180.00);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (IncorrectInputException e) {
            fail("Exception should not have been thrown");
        }
    }
}
