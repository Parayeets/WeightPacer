package model;

import java.lang.StrictMath.*;

import model.Records;

public class User {
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
    public User(String personName, double initialMass, double finalDesiredMass) {
        name = personName;
        this.initialMass = initialMass;
        this.finalDesiredMass = finalDesiredMass;
    }

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


}


