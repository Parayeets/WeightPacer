package ui.compartments;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContinueWeightPacer extends JFrame implements ActionListener {

    JTextArea instructionsText;
    JButton inputNewMassButton;
    Records recordsList;
    Double newMass;
    User newUser;
    Integer result;

    JPanel panel = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();

    public ContinueWeightPacer() {
        /*this.setMinimumSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setMinimumSize(new Dimension(500, 500));

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Continue WeightPacer");
        this.pack();
        this.setVisible(true);*/

        setItems();

        inputNewMass();

        inputNewMassButton();

    }

    private void setItems() {
        instructionsText = new JTextArea("Welcome back " + newUser.getName()
                + ", continue your weight loss journey by inputting today's weight (lbs): ");

        JTextField newMass = new JTextField(10);

        panel = new JPanel();
        panel.add(instructionsText);
        instructionsText.setEditable(false);
        panel.add(new JLabel("New Mass (lbs)"));
        panel.add(newMass);

        /*this.result = JOptionPane.showConfirmDialog(null, panel,
                "Continue Weight Pacer", JOptionPane.OK_CANCEL_OPTION);
        if (this.result == JOptionPane.OK_OPTION) {
            this.newMass =  Double.parseDouble(newMass.getText());
            gui.setUser(new User(userName, initialMass, finalDesiredMass));
            newUser = gui.getUser();
            gui.setUserRecords(new Records(newUser));
            userRecords = gui.getUserRecords();
        }*/
    }

    private void inputNewMass() {
        JTextField newMassInput = new JTextField();
    }

    private void inputNewMassButton() {
        inputNewMassButton = new JButton("Add Today's Mass");
        panel.add(inputNewMassButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputNewMassButton) {
            DailyRecord newDailyRecord = new DailyRecord(newMass);
            recordsList.addDailyRecord(newDailyRecord);
        }
    }
}
