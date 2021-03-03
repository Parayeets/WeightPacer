package model;

import java.util.ArrayList;
import model.*;


public class Records {

    private final ArrayList<DailyRecord> recordsList;


    // EFFECTS: Sets up a constructor called records list and has a list of daily records added.
    public Records() {
        this.recordsList = new ArrayList<>();

    }

    // EFFECTS: Adds a new daily record mass to the arraylist
    public void addDailyRecord(DailyRecord newDailyRecord) {
        //DailyRecord mass = new DailyRecord();
        recordsList.add(newDailyRecord);

    }

    // REQUIRES: Have at least a non-empty Record list.
    // EFFECTS: Removes a new daily record mass to the arraylist
    public void removeDailyRecord() {
        //DailyRecord mass = new DailyRecord(newMass);
        recordsList.remove(getSize() - 1);

    }

    // REQUIRES: Have at least a non-empty Record list.
    // EFFECTS: Reveals a list of daily records.
    public ArrayList<DailyRecord> getRecordsList() {
        return recordsList;

    }

    // EFFECTS: Gets the size of the Records.
    public int getSize() {
        return recordsList.size();
    }

    // EFFECTS: Checks to see if a record contains a certain record.
    public boolean containsRecord(DailyRecord newDailyRecord) {
        return recordsList.contains(newDailyRecord);
    }

}


// Initial mass to be in first element of list --> worry about that in the ui
