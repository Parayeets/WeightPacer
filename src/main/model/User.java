package model;

import java.lang.StrictMath.*;

import org.json.JSONObject;
import persistence.Writable;

public class User implements Writable {
    public static final double RECOMMENDED_WEIGHT_LOSS_PER_DAY = 0.285142857; //(lbs/day) and equates to ~2 lbs a week

    private String name;              // name of user doing a weight loss campaign
    private double initialMass;       // user's initial mass in lbs
    private double finalDesiredMass;  // user's final goal in lbs
    private int difference; // difference between final desired mass - initial mass
    private int trajectory; // how long it is recommended to lose this weight
    //private Records r;      // list of daily records plus initial mass for person

    // REQUIRES: personName is a non-zero length and
    //           finalDesiredMass needs to be lower than initialMass
    // EFFECTS: Sets up a user's information containing their name, their initial mass in pounds
    public User(String personName, double initialMass, double finalDesiredMass) throws IncorrectInputException {
        name = personName;
        if (finalDesiredMass >= initialMass) {
            throw new IncorrectInputException();
            // you could write it in here or you can do it overall, if you're reusing it, write it here,
            // if not, write it in general exception class
        } else if (initialMass > finalDesiredMass) {
            this.initialMass = initialMass;
            this.finalDesiredMass = finalDesiredMass;
        }
        // no need to use try/catch because you're not using a method that throws an exception
    }
    // we do try/catch for checked exceptions only (we can do for unchecked exceptions, but not required to)

    // EFFECTS: Returns name.
    public String getName() {
        return name;
    }

    // EFFECTS: Returns initial mass in lbs.
    public double getInitialMass() {
        return initialMass;
    }

    // EFFECTS: Returns final desired mass in lbs.
    public double getFinalDesiredMass() {
        return finalDesiredMass;
    }

    // MODIFIES: this.
    // EFFECTS: Gives a trajectory in days of how long it will take to reach desired mass from initial mass
    public int initialTrajectoryTowardsGoal(double initialMass, double finalDesiredMass) {
        difference = (int)Math.abs(Math.round(finalDesiredMass - initialMass));
        trajectory = (int)Math.round((difference / RECOMMENDED_WEIGHT_LOSS_PER_DAY));
        return trajectory;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("initial mass", initialMass);
        json.put("final desired mass", finalDesiredMass);
        json.put("initial trajectory", trajectory);
        return json;
    }


}


