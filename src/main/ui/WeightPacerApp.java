package ui;

import model.*;
import java.util.Scanner;

// WeightPacerApp user interface (taken inspiration from TellerApp)
public class WeightPacerApp {
    private Scanner input;
    private Records records;

    //EFFECTS: Runs the WeightPacer App.
    public WeightPacerApp() {
        records = new Records();
        input = new Scanner(System.in);
        //runWeightPacer();
    }

    //EFFECTS: Shows a menu of options for user when they come across the main menu
    private void userInterfaceMenu() {
        System.out.println("\nAre you ready to pace your weight loss campaign today?");
        System.out.println("\tgo   -> Let's lose some weight!");
        System.out.println("\tquit -> Not now.");
    }

}
