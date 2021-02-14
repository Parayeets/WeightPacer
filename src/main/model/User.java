package model;

public class User {

    private String name;              // name of user doing a weight loss campaign
    private double initialMass;       // user's initial mass in lbs
    private double finalDesiredMass;  // user's final goal in lbs

    // REQUIRES: personName is a non-zero length and
    //           finalDesiredMass needs to be lower than initialMass
    // EFFECTS: Sets up a user's information containing their name, their initial mass in pounds
    public User(String personName, double initialMass, double finalDesiredMass) {
        name = personName;
        this.initialMass = initialMass;
        if (finalDesiredMass >= initialMass) {
            System.out.println("Your final desired mass needs to less than your initial mass.");
        } else {
            this.finalDesiredMass = finalDesiredMass;
        }
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


}


