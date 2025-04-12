import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class RaceSettingsGUI {
    private static JFrame settingsFrame;

    public static void createFrame() {
        settingsFrame = new JFrame("Race Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(600, 400);
        settingsFrame.setLayout(new GridBagLayout());
    
        SpinnerNumberModel raceLengthModel = new SpinnerNumberModel(10, 10, 60, 1);
        JSpinner raceLengthSpinner = new JSpinner(raceLengthModel);
    
        SpinnerNumberModel lanesModel = new SpinnerNumberModel(3, 3, 10, 1);
        JSpinner lanesSpinner = new JSpinner(lanesModel);
    
        JPanel raceSettingsPanel = createRaceSettingsPanel(raceLengthSpinner, lanesSpinner);
    
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
    

    private static JPanel createRaceSettingsPanel(JSpinner raceLengthSpinner, JSpinner lanesSpinner) {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setSize(300,200);
    
        panel.add(new JLabel("Race Length:"));
        panel.add(raceLengthSpinner);
    
        panel.add(new JLabel("Lanes:"));
        panel.add(lanesSpinner);
    
        panel.add(new JLabel("Start Race:"));
        JButton startButton = new JButton("Confirm");
        startButton.addActionListener(e -> createRace((int) raceLengthSpinner.getValue(),(int) lanesSpinner.getValue()));
        panel.add(startButton);
    
        return panel;
    }
    

    private static void createRace(int raceLength, int laneCount){
        Race race= new Race(raceLength,laneCount);
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6), 1);
        race.addHorse(new Horse('♞', "KOKOMO", 0.5), 2);
        race.addHorse(new Horse('♛', "EL JEFE", 0.4), 3);

        settingsFrame.dispose();

        race.startRace();
    }

}
