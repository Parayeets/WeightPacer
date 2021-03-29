package ui.compartments;

import model.DailyRecord;
import ui.GUI;

import javax.swing.*;
import java.awt.*;

public class ShowProgress extends JFrame {

    private JList<Double> dailyRecord;

    private final JPanel panel;
    private final JFrame frame;
    private final GridBagConstraints gbc;

    // EFFECTS: Constructs a new window where one can view their list of masses.
    public ShowProgress(JFrame frame) {
        this.frame = frame;
        frame.setMinimumSize(new Dimension(250, 300));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Your Current Progress");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        showList();

    }

    // MODIFIES: This.
    // EFFECTS: Shows the list of masses within Records in chronological order,
    // and created it to be in the centre of the page with list going downwards.
    private void showList() {
        DefaultListModel<Double> listModel = new DefaultListModel<>();
        for (DailyRecord dailyRecord : GUI.getUserRecords().getRecordsList()) {
            listModel.addElement(dailyRecord.getNewMass());
        }

        dailyRecord = new JList<>(listModel);
        panel.add(new JScrollPane(dailyRecord), gbc);

        repaint();
        revalidate();

    }


}


