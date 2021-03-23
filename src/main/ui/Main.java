package ui;

import ui.compartments.IntroSound;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new GUI();
            IntroSound.introSound();
            new WeightPacerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to run application - File not found.");
        }

    }
}
