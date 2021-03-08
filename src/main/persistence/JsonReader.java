package persistence;

import model.DailyRecord;
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

    public Records read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject((jsonData));
        return parseRecords(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Records parseRecords(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double initialMass = jsonObject.getDouble("initial mass");
        Double finalDesiredMass = jsonObject.getDouble("final desired mass");

        User newUser = new User(name, initialMass, finalDesiredMass);

        Records r = new Records();
        addDailyRecords(r, jsonObject);
        return r;
    }

    private void addDailyRecords(Records r, JSONObject jsonObject) {
        //Double newMass = new Double();
        JSONArray jsonArray = jsonObject.getJSONArray("mass");
        for (Object json : jsonArray) {
            JSONObject nextDailyRecord = (JSONObject) json;
            addDailyRecord(r, nextDailyRecord);
        }
    }

    private void addDailyRecord(Records r, JSONObject jsonObject) {
        Double mass = jsonObject.getDouble("new mass");
        //int trajectory = jsonObject.getInt("current trajectory");
        DailyRecord dailyRecord = new DailyRecord(mass);
        r.addDailyRecord(dailyRecord);

    }




}
