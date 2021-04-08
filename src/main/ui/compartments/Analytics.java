package ui.compartments;

import model.DailyRecord;
import ui.GUI;

import javax.swing.*;
import model.*;

public class Analytics extends JFrame {

    private JPanel panel;

    private Integer fetchCurrentMass;
    private Double currentMassInDouble;
    private Double finalDesiredMass;

    private Records userRecords;
    private DailyRecord mostRecentDailyRecord;

    // EFFECTS: Constructs an analytics popup where you get your
    // current trajectory given your most recent inputted mass.
    public Analytics() {
        panel = new JPanel();
        init();
    }

    // MODIFIES: This.
    // EFFECTS: Fetches and sets the current mass and the final desired mass
    // to then be compared in trajectoryText().
    private void init() {
        fetchCurrentMass = GUI.getUserRecords().getRecordsList().size() - 1;
        userRecords = GUI.getUserRecords();
        mostRecentDailyRecord = userRecords.getRecordsList().get(fetchCurrentMass);
        currentMassInDouble = mostRecentDailyRecord.getNewMass();
        finalDesiredMass = GUI.getUser().getFinalDesiredMass();

        trajectoryText();
    }

    // EFFECTS: Given the current mass, determine whether the user
    // has met their goal when comparing it to their finalDesiredMass.
    private void trajectoryText() {
        if (currentMassInDouble <= finalDesiredMass) {
            JOptionPane.showMessageDialog(this,
                    "You have reached your goal! Congratulations!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Keep going! Remember what drives you to lose that weight!"
                            + " Your current trajectory to reach your goal is in "
                            + mostRecentDailyRecord.currentTrajectoryTowardsGoal(GUI.getUser())
                            + " days");
        }

    }

}
