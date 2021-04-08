package model;

import org.json.JSONObject;
import persistence.Writable;

public class DailyRecord implements Writable {
    public static final double RECOMMENDED_WEIGHT_LOSS_PER_DAY = 0.285142857; //(lbs/day) and equates to ~2 lbs a week

    private double newMass; //
    private int currentDifference; // difference between final desired mass - current mass
    private int trajectory; // how long it is recommended to lose this weight

    // REQUIRES: Needs to have a dailyRecord with a newMass of the new day that the mass was taken
    // EFFECTS: Each day, a newMass is inputted daily, hence DailyRecord (which will go into a list of Records)
    public DailyRecord(double newMass) {
        this.newMass = newMass;
    }

    // EFFECTS: Returns the newMass.
    public double getNewMass() {
        return newMass;
    }

    // MODIFIES: this.
    // EFFECTS: Checks to see whether the user reached or equalled their desired goal.
    // If so, return true. Otherwise return false.
    public Boolean reachedGoal(User mass) {
        return newMass <= mass.getFinalDesiredMass();
    }

    // MODIFIES: newMass, finalDesiredMass
    // EFFECTS: Give the trajectory in days that it will take for one to reach their weight goals
    public int currentTrajectoryTowardsGoal(User user) {
        currentDifference = (int)Math.abs(Math.round(user.getFinalDesiredMass() - newMass));
        trajectory = (int)Math.round(currentDifference / RECOMMENDED_WEIGHT_LOSS_PER_DAY);
        return trajectory;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("new mass", newMass);
        return json;

    }

}
