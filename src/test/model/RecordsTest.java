package model;

import model.Records;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecordsTest {

    private Records testRecord;
    private User testUser;

    private DailyRecord testDailyRecordOne;
    private DailyRecord testDailyRecordTwo;
    private DailyRecord testDailyRecordThree;

    @BeforeEach
    void runBefore() {
        testUser = new User("Quinn", 120, 115);
        testRecord = new Records(testUser);
        testDailyRecordOne = new DailyRecord(102.00);
        testDailyRecordTwo = new DailyRecord(101.00);
        testDailyRecordThree = new DailyRecord(102.50);

    }

    @Test
    void testConstructor() {
        assertEquals(testRecord.getSize(), 0);

    }

    @Test
    void testAddDailyRecordToEmptyRecordsList() {
        assertEquals(testRecord.getSize(), 0);
        testRecord.addDailyRecord(testDailyRecordOne);
        assertEquals(testRecord.getSize(), 1);
    }

    @Test
    void testAddDailyRecordToNonEmptyRecordsList() {
        testRecord.addDailyRecord(testDailyRecordTwo);
        assertEquals(testRecord.getSize(), 1);
        testRecord.addDailyRecord(testDailyRecordOne);
        assertEquals(testRecord.getSize(), 2);
    }

    @Test
    void testAddSameDailyRecordToNonEmptyRecordsList() {
        testRecord.addDailyRecord(testDailyRecordTwo);
        assertEquals(testRecord.getSize(), 1);
        testRecord.addDailyRecord(testDailyRecordTwo);
        assertEquals(testRecord.getSize(), 2);
    }

    @Test
    void testAddTwoDailyRecordsToNonEmptyRecordsList() {
        testRecord.addDailyRecord(testDailyRecordThree);
        assertEquals(testRecord.getSize(), 1);
        testRecord.addDailyRecord(testDailyRecordOne);
        testRecord.addDailyRecord(testDailyRecordTwo);
        assertEquals(testRecord.getSize(), 3);
    }

    @Test
    void testRemoveDailyRecordFromNonEmptyList() {
        testRecord.addDailyRecord(testDailyRecordThree);
        testRecord.addDailyRecord(testDailyRecordOne);
        assertEquals(testRecord.getSize(), 2);
        testRecord.removeDailyRecord();
        assertEquals(testRecord.getSize(), 1);
        assertTrue(testRecord.containsRecord(testDailyRecordThree));
    }

    @Test
    void testRemoveDailyRecordFromBiggerNonEmptyList() {
        testRecord.addDailyRecord(testDailyRecordThree);
        testRecord.addDailyRecord(testDailyRecordOne);
        testRecord.addDailyRecord(testDailyRecordTwo);
        assertEquals(testRecord.getSize(), 3);
        testRecord.removeDailyRecord();
        assertEquals(testRecord.getSize(), 2);
        assertTrue(testRecord.containsRecord(testDailyRecordOne));
        assertTrue(testRecord.containsRecord(testDailyRecordThree));
    }

    @Test
    void testReturnRecordListWithOneRecord() {
        testRecord.addDailyRecord(testDailyRecordOne);
        testRecord.getRecordsList();
        assertTrue(testRecord.containsRecord(testDailyRecordOne));

    }

    @Test
    void testReturnRecordListWithMultipleRecords() {
        testRecord.addDailyRecord(testDailyRecordOne);
        testRecord.addDailyRecord(testDailyRecordTwo);
        testRecord.getRecordsList();
        assertTrue(testRecord.containsRecord(testDailyRecordOne));
        assertTrue(testRecord.containsRecord(testDailyRecordTwo));

    }

    @Test
    void testToJson() {
        JSONObject json = testUser.toJson();
        //assertEquals(json.get("initialUserInformation"), testUser.toJson());
        //assertEquals(json.get("recordsList"), testRecord.getRecordsList());
    }

    @Test
    void testRecordsToJson() {
        JSONArray json = testRecord.recordsToJson();
    }


}
