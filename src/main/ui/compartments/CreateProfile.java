package ui.compartments;

import javax.swing.*;

import model.*;
import ui.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Took inspiration from the https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html tutorial
//  as well as https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html tutorial.
public class CreateProfile extends JFrame {


    JTextArea instructionsText = new JTextArea("Great to have you with us!\nInsert your name, "
                                                    + "current weight, and your final goal (in pounds).");
    private User newUser;
    private Records userRecords;
    private String userName;
    private Double initialMass;
    private Double finalDesiredMass;
    // private GUI gui;
    private Integer result;
    private JPanel panel;

    //JPanel topPanel = new JPanel(new GridLayout(3, 1));
    //JPanel bottomPanel = new JPanel();
    //GridBagConstraints gbcOne = new GridBagConstraints();

    // EFFECTS: Constructs a new profile environment for the user to put in their information.
    public CreateProfile() {
        panel = new JPanel();
        //gui = new GUI();

        //this.setMinimumSize(new Dimension(500, 500));
        //gbcOne.fill = GridBagConstraints.HORIZONTAL;

        //this.add(topPanel, BorderLayout.NORTH);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setTitle("Create a Profile of Yourself!");
        //this.pack();
        //this.setVisible(true);


        setItems(); //initializes the 3 blanks that need to be filled as well as the instructions
        //setButton(); //initializes the button
        //setAction(); //adds the ActionListener

        //inputInfo();
    }

    private void setItems() {
        /* // Code below taken straight from the SpringLayout Tutorial as it's exactly what I need
        String[] labels = {"Name: ", "Initial Mass (lbs): ", "Goal (lbs): "};
        int numPairs = labels.length;
        this.middlePanel = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            middlePanel.add(l);
            JTextField textField = new JTextField(10);
            l.setLabelFor(textField);
            middlePanel.add(textField);

            //SpringUtilities.makeCompactGrid(middlePanel, numPairs, 1, 6, 6, 6, 6);

        }
        topPanel.add(middlePanel); */
        JTextField name = new JTextField(20);
        JTextField initial = new JTextField(10);
        JTextField goal = new JTextField(10);
        // init()

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
        // add components to panel ()

        this.result = JOptionPane.showConfirmDialog(null, panel,
                "Create Your Own Weight Loss Profile", JOptionPane.OK_CANCEL_OPTION);
        if (this.result == JOptionPane.OK_OPTION) {
            userName =  name.getText();
            initialMass = Double.parseDouble(initial.getText());
            finalDesiredMass = Double.parseDouble(goal.getText());
            GUI.setUser(new User(userName, initialMass, finalDesiredMass));

            newUser = GUI.getUser();
            Records record = new Records((newUser));
            record.addDailyRecord(new DailyRecord(initialMass));
            GUI.setUserRecords(record);
            userRecords = GUI.getUserRecords();
            actionPerformed();
        }

    }


    // MODIFIES: This.
    // EFFECTS: Displays the confirm button at the bottom right of the page.
    /*private void setButton() {
        confirmButton = new JButton("Confirm Inputs");

        // Code below taken straight from the GridBag Tutorial as it's exactly what I need
        GridBagConstraints gbcTwo = new GridBagConstraints();
        gbcTwo.fill = GridBagConstraints.HORIZONTAL;
        gbcTwo.ipady = 0;       //reset to default
        gbcTwo.weighty = 1.0;   //request any extra vertical space
        gbcTwo.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbcTwo.insets = new Insets(10,0,0,0);  //top padding
        gbcTwo.gridx = 1;       //aligned with button 2
        gbcTwo.gridwidth = 2;   //2 columns wide
        gbcTwo.gridy = 2;       //third row

        topPanel.add(bottomPanel);
        bottomPanel.add(confirmButton, gbcTwo);
    }*/


    // MODIFIES: This.
    // EFFECTS: Constructs an ActionListener for confirmButton at the bottom right corner of the window.
    private void setAction() {

//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                actionPerformed(e);
//            }
//        });
    }

    /*private void inputInfo() {

    }*/

    // MODIFIES: This.
    // EFFECTS: Performs either two pop-ups. One is if the goal is greater than the initial mass then
    //          reject that option and say a message to try again. The other is that if everything
    //          is inputted correctly, then create a pop-up window of how many days it will take to reach their goal.
    public void actionPerformed() {
        if (result == JOptionPane.OK_OPTION && finalDesiredMass >= initialMass) {
            JOptionPane.showMessageDialog(this,
                    "Sorry, your goal needs to be less than "
                            + "your initial mass. Please try again!",
                    "Inane Error", JOptionPane.ERROR_MESSAGE);
        } else if (result == JOptionPane.OK_OPTION) {
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
