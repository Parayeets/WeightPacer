package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sun.codemodel.internal.JOp;
import model.*;
import persistence.*;
import ui.compartments.*;

// TODO: Took inspiration from Alex Lee's YouTube tutorial on a GUI.
// TODO: Took inspiration from
//  https://stackoverflow.com/questions/22933507/how-can-i-place-buttons-in-the-center-of-the-frame-in-a-vertical-line
public class GUI extends JFrame {

    JTextArea welcomeText;
    JButton newWeightPacer;
    JButton saveProgress;
    JButton loadProgress;
    JButton continueWeightPacer;
    JButton analytics;
    JButton showProgress;

    private static final String JSON_STORE = "./data/records.json";
    GridBagConstraints gbc = new GridBagConstraints();

    private static Records userRecords;
    private static User user;
    double initialMass;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    JPanel panel;

    public GUI() {
        init();

        this.setMinimumSize(new Dimension(500, 500));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setMinimumSize(new Dimension(500, 500));
        //panel.setBorder();

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("WeightPacer");
        this.pack();
        this.setVisible(true);
        // Instead of instantiating a new JFrame, just have the GUI extend JFrame
        // and use "this" to refer to JFrame instead. It's cleaner and reduces clutter.


        setItems(); //initialize the buttons

        setAction(); //add the actionListener to the fields


    }

    private void init() {
        user = new User("Guest", 0, 0);
        userRecords = new Records(user);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public static void setUserRecords(Records records) {
        GUI.userRecords = records;
    }

    public static Records getUserRecords() {
        return GUI.userRecords;
    }

    public static void setUser(User user) {
        GUI.user = user;
    }

    public static User getUser() {
        return GUI.user;
    }

    // MODIFIES: This.
    // EFFECTS: Displays the six buttons that the user can interact with.
    private void setItems() {
        welcomeText = new JTextArea("Welcome! Please select one of the following:");
        panel.add(welcomeText, gbc);
        newWeightPacer = new JButton("Begin New WeightPacer");
        panel.add(newWeightPacer, gbc);
        saveProgress = new JButton("Save Progress");
        panel.add(saveProgress, gbc);
        loadProgress = new JButton("Load Progress");
        panel.add(loadProgress, gbc);
        continueWeightPacer = new JButton("Continue Current WeightPacer");
        panel.add(continueWeightPacer, gbc);
        analytics = new JButton("Analytics");
        panel.add(analytics, gbc);
        showProgress = new JButton("Show Progress");
        panel.add(showProgress, gbc);

        welcomeText.setEditable(false);
    }

    // MODIFIES: This.
    // EFFECTS: Creates ActionListeners for every option chosen by the user in the menu.
    private void setAction() {
        newWeightPacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateProfile();
            }
        });

        saveProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecords();
            }
        });
        loadProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecords();
            }
        });
        continueWeightPacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContinueWeightPacer();
            }
        });
        analytics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Analytics();
            }
        });
        showProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowProgress();
            }
        });

    }

    // MODIFIES: This.
    // EFFECTS: Performs the option that is selected by the user.
    /*public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWeightPacer) {
            new CreateProfile();
        } else if (e.getSource() == saveProgress) {
            saveRecords();
        } else if (e.getSource() == loadProgress) {
            loadRecords();
        } else if (e.getSource() == continueWeightPacer) {
            new ContinueWeightPacer();
        } else if (e.getSource() == analytics) {
            //create new class that simply shows you your analytics
        } else if (e.getSource() == showProgress) {
            //create new class that returns list of records
        }
    }*/

    // MODIFIES: This.
    // EFFECTS: Saves the records into "./data/records.json" (JSON_STORE).
    public void saveRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(userRecords);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Saved " + userRecords.getRecordsList() + "to " + JSON_STORE);
            //System.out.println("Saved " + userRecords.getRecordsList() + "to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE,
                    "Inane Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: This.
    // EFFECTS: Loads the records from "./data/records.json" (JSON_STORE).
    public void loadRecords() {
        try {
            userRecords = jsonReader.read();
            user = userRecords.getUser();
            JOptionPane.showMessageDialog(this,
                    "Loaded " + userRecords.getRecordsList() + "from " + JSON_STORE);
            //System.out.println("Loaded " + userRecords.getRecordsList() + "from " + JSON_STORE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Unable to read file: " + JSON_STORE,
                    "Inane Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
