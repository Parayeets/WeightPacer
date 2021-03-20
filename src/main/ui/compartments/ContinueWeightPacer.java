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

    JPanel panel = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();

    public ContinueWeightPacer() {
        this.setMinimumSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setMinimumSize(new Dimension(500, 500));

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Continue WeightPacer");
        this.pack();
        this.setVisible(true);

        setItems();

        inputNewMass();

        inputNewMassButton();

    }

    private void setItems() {
        instructionsText = new JTextArea("Welcome back " + newUser.getName()
                + ", continue your weight loss journey by inputting today's weight (lbs): ");
        panel.add(instructionsText);
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
