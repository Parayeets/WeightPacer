package model;

import model.User;
import java.lang.StrictMath.*;

public class DailyRecord {
    public static final double RECOMMENDED_WEIGHT_LOSS_PER_DAY = 0.285142857; //(lbs/day) and equates to ~2 lbs a week

    private double newMass; //
    private int difference; // difference between final desired mass - initial mass
    private int trajectory; // how long it is recommended to lose this weight

    // REQUIRES:
    // EFFECTS:
    public DailyRecord(double newMass) {
        this.newMass = newMass;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public Boolean reachedGoal(User mass) {
        if (newMass <= mass.getFinalDesiredMass()) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: N/A
    // MODIFIES: newMass, finalDesiredMass
    // EFFECTS: Give the trajectory in days that it will take for one to reach their weight goals
    public int trajectoryTowardsGoal(User mass) {
        //difference = Math.abs(Math.round(mass.getFinalDesiredMass() - newMass));
        //trajectory = Math.round(difference / RECOMMENDED_WEIGHT_LOSS_PER_DAY);
        return trajectory;
    }

}
