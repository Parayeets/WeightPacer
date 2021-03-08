package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import model.*;


public class Records implements Writable {

    private final ArrayList<DailyRecord> recordsList;
    private User user;


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



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recordsList", recordsToJson());
        return json;
    }


    // EFFECTS: Returns things in the Records as a JSON array.
    public JSONArray recordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DailyRecord records : recordsList) {
            jsonArray.put(records.toJson());
        }

        return jsonArray;
    }

}


// Initial mass to be in first element of list --> worry about that in the ui
