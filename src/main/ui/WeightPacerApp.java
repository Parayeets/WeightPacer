package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// WeightPacerApp user interface (taken inspiration from TellerApp and WorkRoom app)
public class WeightPacerApp {
    private static final String JSON_STORE = "./data/records.json";
    private Scanner input;
    private Records userRecords;
    private User user;
    double initialMass;
    // include name in asking questions (do this later)

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Runs the WeightPacer App.
    public WeightPacerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        userRecords = new Records(this.user);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        System.out.println("\tsave -> save records to file");
        System.out.println("\tload -> load records from file");
        System.out.println("\tcontinue   -> Let's continue!");
        System.out.println("\tanalytics   -> Display analytics!");
        System.out.println("\tlist   -> Show current progress!");
        System.out.println("\tquit -> Not now.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("go")) {
            startUp();
        } else if (command.equals("save")) {
            saveRecords();
        } else if (command.equals("load")) {
            loadRecords();
        } else if (command.equals("continue")) {
            addTodaysDailyRecord();
        } else if (command.equals("analytics")) {
            currentTrajectory();
        } else if (command.equals("list")) {
            showList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        userRecords = new Records(this.user);
        input = new Scanner(System.in);
    }



    // Creates a new user, inputs initial mass in lbs and inputs final desired mass as well.
    private void startUp() {
        String name = null;
        double finalDesiredMass;
        System.out.println("To create a new WeightPacer, please insert your name");
        name = input.next();
        System.out.println("Please insert your current mass in lbs");
        initialMass = input.nextDouble();
        DailyRecord im = new DailyRecord(initialMass);
        userRecords.addDailyRecord(im);
        while (true) {
            System.out.println("Please insert your final desired mass in lbs");
            finalDesiredMass = input.nextDouble();
            try {
                this.user = new User(name, initialMass, finalDesiredMass);
                break;
            } catch (IncorrectInputException e) {
                System.out.println("You need to have a final desired mass that's lower than the initial mass");
            }
//            if (finalDesiredMass < initialMass) {
//                break;
//            } else {
//                System.out.println("You need to have a final desired mass that's lower than the initial mass");
//            }
        }
        this.userRecords = new Records(this.user);

        System.out.println("Using your given data, according to health experts, it is recommended"
                + " to lose only 2 lbs a week. Given those calculations, your approximate trajectory to"
                + " meet your goal is in " + user.initialTrajectoryTowardsGoal(user.getInitialMass(),
                user.getFinalDesiredMass()) + " days.");

    }



    // EFFECTS: Saves records to file.
    private void saveRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(userRecords);
            jsonWriter.close();
            System.out.println("Saved " + userRecords.getRecordsList() + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }



    // MODIFIES: This.
    // EFFECTS: Loads records to file.
    private void loadRecords() {
        try {
            userRecords = jsonReader.read();
            user = userRecords.getUser();
            System.out.println("Loaded " + userRecords.getRecordsList() + "from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (IncorrectInputException e) {
            System.out.println(e.getMessage());
        }
    }



    // EFFECTS: Continues on weight loss journey by adding a new mass daily.
    private void addTodaysDailyRecord() {
        double todaysMass;
        System.out.println("Hello again, continue your weight loss journey by inputting your today's weight");
        todaysMass = input.nextDouble();
        DailyRecord newDailyRecord = new DailyRecord(todaysMass);
        userRecords.addDailyRecord(newDailyRecord);
    }

    // MODIFIES: This.
    // EFFECTS: Tells you your trajectory in days given your most recent weight input
    //          and also determines if you've made your goal or not.
    private void currentTrajectory() {
        ArrayList<DailyRecord> recordsList = userRecords.getRecordsList();
        if (recordsList.size() == 0) {
            System.out.println("You aren't able to get your trajectory. Your list of records are empty!");
            return;
        }
        DailyRecord todaysTrajectory = recordsList.get(recordsList.size() - 1);
        int tj = todaysTrajectory.currentTrajectoryTowardsGoal(this.user);
        if (recordsList.get(recordsList.size() - 1).reachedGoal(this.user)) {
            System.out.println("You have reached your goal! Congratulations!");
        } else {
            System.out.println("Keep going! Remember what drives you to lose that weight!");
            System.out.println("Your current trajectory to reach your goal is in " + tj + " days");
        }
    }


    // EFFECTS: Shows the list of records.
    private void showList() {
        ArrayList<DailyRecord> recordsList = userRecords.getRecordsList();
        if (recordsList.size() == 0) {
            System.out.println(initialMass);
            return;
        }
        System.out.println(user.getInitialMass());
        for (DailyRecord record : recordsList) {
            System.out.println(record.getNewMass());
        }
    }




}
