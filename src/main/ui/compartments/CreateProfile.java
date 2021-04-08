package ui.compartments;

import javax.swing.*;

import model.*;
import ui.GUI;

// TODO: Took inspiration from the https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html tutorial
//  as well as https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html tutorial.
public class CreateProfile extends JFrame {


    JTextArea instructionsText = new JTextArea("Great to have you with us!\nInsert your name, "
                                                    + "current weight, and your final goal (in pounds).");
    JTextField name;
    JTextField initial;
    JTextField goal;

    private User newUser;
    private User guestUser;
    private Records userRecords;

    private String userName;
    private Double initialMass;
    private Double finalDesiredMass;

    private Integer result;

    private JPanel panel;

    //JPanel topPanel = new JPanel(new GridLayout(3, 1));
    //JPanel bottomPanel = new JPanel();
    //GridBagConstraints gbcOne = new GridBagConstraints();

    // EFFECTS: Constructs a new profile environment for the user to put in their information.
    public CreateProfile() {
        panel = new JPanel();

        initItems(); //initializes the main popup for creating a profile

        setItems();  //sets up new values that goes within the popup when creating a profile

    }

    // EFFECTS: Initializes three JTextFields (and created labels accordingly) to put values in.
    //          It initializes a name, initial mass, and a goal.
    private void initItems() {
        name = new JTextField(20);
        initial = new JTextField(10);
        goal = new JTextField(10);

        panel = new JPanel();
        panel.add(instructionsText);
        instructionsText.setEditable(false);
        panel.add(new JLabel("Name"));
        panel.add(name);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(new JLabel("Initial Mass (lbs)"));
        panel.add(initial);
        panel.add(new JLabel("Goal (lbs)"));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(goal);
    }

    // MODIFIES: This.
    // EFFECTS: Takes the user input and sets the name, initial mass and goal to those inputs.
    private void setItems() {
        guestUser = GUI.getUser();
        while (GUI.getUser() == guestUser) {
            this.result = JOptionPane.showConfirmDialog(null, panel,
                    "Create Your Own Weight Loss Profile", JOptionPane.OK_CANCEL_OPTION);
            if (this.result == JOptionPane.OK_OPTION) {
                userName = name.getText();
                initialMass = Double.parseDouble(initial.getText());
                finalDesiredMass = Double.parseDouble(goal.getText());
                try {
                    GUI.setUser(new User(userName, initialMass, finalDesiredMass));
                } catch (IncorrectInputException e) {
                    inputErrorDialog();
                }
            // we call try here, because we're calling a method
                newUser = GUI.getUser();
                Records record = new Records((newUser));
                record.addDailyRecord(new DailyRecord(initialMass));
                GUI.setUserRecords(record);
                userRecords = GUI.getUserRecords();
                actionPerformed();
            }
        }
    }

    // EFFECTS: Performs a pop-up where if the goal is greater than the initial mass then
    //          reject that option and say a message to try again.
    private void inputErrorDialog() {
        JOptionPane.showMessageDialog(this,
                "Sorry, your goal needs to be less than "
                       + "your initial mass. Please try again!",
                "Inane Error", JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: This.
    // EFFECTS: Performs a pop-up where if everything is inputted correctly,
    // then create a pop-up window of how many days it will take to reach their goal.
    public void actionPerformed() {
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "According to Mayo Clinic, it is recommended"
                            + " to lose a maximum 2 lbs per week (or ~0.29 lbs a day). "
                            + "Given the recommended rate of weight loss per week, "
                            + "your approximate trajectory to meet your goal is in "
                            + newUser.initialTrajectoryTowardsGoal(newUser.getInitialMass(),
                            newUser.getFinalDesiredMass()) + " days.");
        }
    }


}

// create field guestuser
// assign guestuser field to start of program (main)
// (while GUI.getUser == guestUser)
// keepgoing;