package persistence;

import model.IncorrectInputException;
import model.User;
import org.json.JSONObject;

public interface Writable {
    // EFFECTS: Returns this as a JSON object
    JSONObject toJson();

}
