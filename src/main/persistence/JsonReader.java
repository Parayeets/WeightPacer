package persistence;

import model.DailyRecord;
import model.IncorrectInputException;
import model.Records;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.User;
import org.json.*;

// TODO: Took inspiration from JsonReader.java package in JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: Creates a JsonReader constructor to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Creates a read method that allows data to be parsed.
    public Records read() throws IOException, IncorrectInputException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecords(jsonObject);
    }

    // EFFECTS: Reads the source file.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: Parses the initial user information and records to then load.
    private Records parseRecords(JSONObject jsonObject) throws IncorrectInputException {
        JSONObject userinfo = jsonObject.getJSONObject("initialUserInformation");
        String name = userinfo.getString("name");
        Double initialMass = userinfo.getDouble("initial mass");
        Double finalDesiredMass = userinfo.getDouble("final desired mass");
        //int initialTrajectory = userinfo.getInt("initial trajectory");


        User newUser = new User(name, initialMass, finalDesiredMass);
        newUser.initialTrajectoryTowardsGoal(initialMass, finalDesiredMass);

        Records r = new Records(newUser);
        JSONArray recordsInfo = jsonObject.getJSONArray("recordsList");
        for (Object recordObject : recordsInfo) {
            addDailyRecord(r, (JSONObject) recordObject, newUser);
        }
        return r;

//        User newUser = new User(name, initialMass, finalDesiredMass);
//        newUser.initialTrajectoryTowardsGoal(initialMass, finalDesiredMass);
//
//        Records r = new Records(newUser);
//        JSONArray recordsInfo = jsonObject.getJSONArray("recordsList");
//        for (Object recordObject : recordsInfo) {
//            addDailyRecord(r, (JSONObject) recordObject, newUser);
//        }
//        return r;
    }

    // EFFECTS: Includes new elements that parseRecords can parse and then be able to load.
    private void addDailyRecord(Records r, JSONObject recordObject, User user) {
        Double newMass = recordObject.getDouble("new mass");
        //int trajectory = recordObject.getInt("current trajectory");
        DailyRecord dailyRecord = new DailyRecord(newMass);
        dailyRecord.currentTrajectoryTowardsGoal(user);
        r.addDailyRecord(dailyRecord);
    }

}
