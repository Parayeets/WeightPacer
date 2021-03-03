package ui;

import model.*;
import java.util.Scanner;

// WeightPacerApp user interface (taken inspiration from TellerApp)
public class WeightPacerApp {
    private Scanner input;
    private Records userRecords;
    private User user;
    private DailyRecord dailyRecord;

    //EFFECTS: Runs the WeightPacer App.
    public WeightPacerApp() {
        runWeightPacer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runWeightPacer() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            userInterfaceMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS: Shows a menu of options for user when they come across the main menu
    private void userInterfaceMenu() {
        System.out.println("\nAre you ready to pace your weight loss campaign today?");
        System.out.println("\tgo   -> Let's lose some weight!");
        System.out.println("\tcontinue   -> Let's continue!");
        System.out.println("\tquit -> Not now.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("go")) {
            startUp();
        } else if (command.equals("continue")) {
            addTodaysDailyRecord();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        this.user = new User("", 000.00, 000.00);
        userRecords = new Records();
        input = new Scanner(System.in);
    }

    // Creates a new user, inputs initial mass in lbs and inputs final desired mass as well
    private void startUp(String name, double initialMass, double finalDesiredMass) {
        System.out.println("To create a new WeightPacer, please insert your name");
        = new name;
        System.out.println("Please insert your current mass in lbs");
        user.getInitialMass() = ;
        System.out.println("Please insert your final desired mass in lbs");
        if (user.getFinalDesiredMass() < user.getInitialMass()) {
            user.getFinalDesiredMass() = ;
        } else {
            System.out.println("You cannot have a final desired mass that's higher than the initial mass");
        }
        System.out.println("Using your given data, according to health experts, it is recommended" +
                "to lose only 2 lbs a week. Given those calculations, your approximate trajectory to" +
                "meet your goal is in" + toString (user.initialTrajectoryTowardsGoal(user.getInitialMass(),
                user.getFinalDesiredMass())));

    }

    // Continues on weight loss journey by adding a new mass daily.
    private void addTodaysDailyRecord() {
        System.out.println("Hello again, continue your weight loss journey by inputting your today's weight");
        dailyRecord.;



    }


}
