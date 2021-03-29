package ui.compartments;

import model.*;
import ui.GUI;

import javax.swing.*;

public class ContinueWeightPacer extends JFrame {

    private JTextField newMassInput;

    JPanel panel;

    // EFFECTS: Constructs a ContinueWeightPacer panel that produces
    // a popup where one can add their current weight to their Records.
    public ContinueWeightPacer() {
        panel = new JPanel();
        initItems();

        setNewMass();
    }

    // EFFECTS: Initializes the popup for adding a new mass to the Records.
    private void initItems() {
        //newUser = GUI.getUser().getName();
        JTextArea instructionsText = new JTextArea("Welcome back,\n"
                + "continue your weight loss journey\nby inputting today's weight (lbs): ");

        newMassInput = new JTextField(10);

        panel = new JPanel();
        panel.add(instructionsText);
        instructionsText.setEditable(false);
        panel.add(new JLabel("New Mass (lbs)"));
        panel.add(newMassInput);
    }

    // MODIFIES: This.
    // EFFECTS: User sets their mass in pounds within the JTextField and then adds that
    // mass to the Records with the list of masses.
    private void setNewMass() {
        int result = JOptionPane.showConfirmDialog(this, panel,
                "Continue Weight Pacer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            double newMass = Double.parseDouble(newMassInput.getText());
            Records userRecords = GUI.getUserRecords();
            DailyRecord dailyRecord = new DailyRecord(newMass);
            userRecords.addDailyRecord(dailyRecord);

            confirmNewMassInput();
        }
    }

    // EFFECTS: Shows a confirmation dialog that the user has successfully added their mass to the Records.
    public void confirmNewMassInput() {
        JOptionPane.showMessageDialog(this,
                    "Successfully added today's mass to your\n"
                            + "current progress list.");

    }
}
