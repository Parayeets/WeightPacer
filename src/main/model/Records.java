package model;

import java.util.ArrayList;

public class Records {

    private ArrayList<DailyRecord> recordsList;


    // EFFECTS: Sets up a constructor called records list and has a list of daily records added.
    public Records() {
        this.recordsList = new ArrayList<>();

    }

    // EFFECTS: Adds a new daily record mass to the arraylist
    public void addDailyRecord(double newMass) {
        DailyRecord mass = new DailyRecord(newMass);
        recordsList.add(mass);

    }

    // REQUIRES: Have at least a non-empty Record list.
    // EFFECTS: Removes a new daily record mass to the arraylist
    public void removeDailyRecord(double newMass) {
        DailyRecord mass = new DailyRecord(newMass);
        recordsList.remove(mass);

    }

    // EFFECTS: Reveals a list of daily records.
    public ArrayList<DailyRecord> getRecordsList() {
        return recordsList;

    }

}
