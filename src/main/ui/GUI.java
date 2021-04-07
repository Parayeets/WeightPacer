package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import persistence.*;
import ui.compartments.*;

// TODO: Took inspiration from Alex Lee's YouTube tutorial on a GUI.
// TODO: Took inspiration from
//  https://stackoverflow.com/questions/22933507/how-can-i-place-buttons-in-the-center-of-the-frame-in-a-vertical-line
public class GUI extends JFrame {

    private JButton newWeightPacer;
    private JButton saveProgress;
    private JButton loadProgress;
    private JButton continueWeightPacer;
    private JButton analytics;
    private JButton showProgress;

    private static final String JSON_STORE = "./data/records.json";

    private static Records userRecords;
    private static User user;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private final JPanel panel;

    private final GridBagConstraints gbc = new GridBagConstraints();

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
        try {
            user = new User("Guest", 1, 0);
        } catch (IncorrectInputException e) {
            System.exit(-1);
        }
        userRecords = new Records(user);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Sets
    public static void setUserRecords(Records records) {
        GUI.userRecords = records;
    }

    // EFFECTS: Fetches the latest UserRecords that's been saved within the project.
    public static Records getUserRecords() {
        return GUI.userRecords;
    }

    // EFFECTS:
    public static void setUser(User user) {
        GUI.user = user;
    }

    // EFFECTS:
    public static User getUser() {
        return GUI.user;
    }

    // MODIFIES: This.
    // EFFECTS: Displays the six buttons that the user can interact with.
    private void setItems() {
        JTextArea welcomeText;

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
        createNewProfile();

        saveCurrentProgress();
        loadCurrentProgress();

        updateListOfMasses();

        showAnalytics();
        showProgressList();

    }

    // EFFECTS:
    private void createNewProfile() {
        newWeightPacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateProfile();
            }
        });
    }

    // EFFECTS:
    private void saveCurrentProgress() {
        saveProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecords();
            }
        });
    }

    // EFFECTS:
    private void loadCurrentProgress() {
        loadProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecords();
            }
        });
    }

    // EFFECTS:
    private void showAnalytics() {
        analytics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Analytics();
            }
        });
    }

    // EFFECTS:
    private void updateListOfMasses() {
        continueWeightPacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContinueWeightPacer();
            }
        });
    }

    // EFFECTS:
    private void showProgressList() {
        showProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                new ShowProgress(frame);
            }
        });
    }

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
        } catch (IncorrectInputException exception) {
            JOptionPane.showMessageDialog(this, "Unable to create user because "
                    + "incorrect inputs.");
        }
    }


}
