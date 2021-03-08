package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new WeightPacerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to run application - File not found.");
        }

    }
}
