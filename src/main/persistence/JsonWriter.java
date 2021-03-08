package persistence;

import model.Records;
import org.json.JSONObject;

import java.io.*;

// TODO: Took inspiration from JsonWriter.java package in JsonSerializationDemo

// Represents a writer that writes JSON representation of Records to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Creates a writer constructor to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: This.
    // EFFECTS: Opens the writer and throws a FileNotFoundException if destination
    //          file cannot be opened for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: This.
    // EFFECTS: Writes a JSON representation of the Records to the file
    public void write(Records r) {
        JSONObject json = r.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: This.
    // EFFECTS: Closes the writer.
    public void close() {
        writer.close();
    }

    // MODIFIES: This.
    // EFFECTS: Writes string to file.
    public void saveToFile(String json) {
        writer.print(json);
    }


}
