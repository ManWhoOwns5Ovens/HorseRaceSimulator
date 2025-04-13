import java.awt.*;
import javax.swing.*;

public class RaceSettingsGUI {
    private static JFrame settingsFrame;

    public static void createFrame() {
        settingsFrame = new JFrame("Race Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(600, 400);
        settingsFrame.setLayout(new GridBagLayout());
    
        JPanel raceSettingsPanel = createRaceSettingsPanel();
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;    
        gbc.gridx = 0;
        gbc.weightx = 0.67;
        settingsFrame.add(Box.createHorizontalStrut(100), gbc); 
    
        gbc.gridx = 1;
        gbc.weightx = 0.33;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsFrame.add(raceSettingsPanel, gbc);
    
        settingsFrame.setVisible(true);
    }
    

    private static JPanel createRaceSettingsPanel() {
        SpinnerNumberModel raceLengthModel = new SpinnerNumberModel(10, 10, 60, 1);
        JSpinner raceLengthSpinner = new JSpinner(raceLengthModel);
    
        SpinnerNumberModel lanesCountModel = new SpinnerNumberModel(3, 3, 12, 1);
        JSpinner lanesCountSpinner = new JSpinner(lanesCountModel);

        JComboBox laneTypeList = new JComboBox(LaneType.values());
        laneTypeList.setSelectedIndex(0);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setSize(300,200);
    
        panel.add(new JLabel("Race Length:"));
        panel.add(raceLengthSpinner);
    
        panel.add(new JLabel("Lanes:"));
        panel.add(lanesCountSpinner);

        panel.add(new JLabel("Lane Type:"));
        panel.add(laneTypeList);
    
        panel.add(new JLabel("Start Race:"));
        JButton startButton = new JButton("Confirm");
        startButton.addActionListener(e -> createRace((int) raceLengthSpinner.getValue(),(int) lanesCountSpinner.getValue(), (LaneType)laneTypeList.getSelectedItem()));
        panel.add(startButton);
    
        return panel;
    }
    

    private static void createRace(int raceLength, int laneCount, LaneType laneType) {
        Race race= new Race(raceLength,laneCount,laneType);
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6), 1);
        race.addHorse(new Horse('♞', "KOKOMO", 0.5), 2);
        race.addHorse(new Horse('♛', "EL JEFE", 0.4), 3);

        settingsFrame.dispose();

        RaceGUI.createFrame(race);
        race.startRace();
    }

}
