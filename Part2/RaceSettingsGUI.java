import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceSettingsGUI {
    private static JFrame settingsFrame;
    private static RaceGUI gui;

    public static void createFrame() {
        settingsFrame = new JFrame("Race Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(1000, 400);
        settingsFrame.setLayout(new GridBagLayout());
    
        JPanel raceSettingsPanel = createRaceSettingsPanel();
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;    
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        gbc.weighty=1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        settingsFrame.add(createHorseConfig(), gbc); 
    
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        settingsFrame.add(raceSettingsPanel, gbc);
    
        settingsFrame.setVisible(true);
    }

    private static JScrollPane createHorseConfig(){
        JPanel configPanel= new JPanel();
        configPanel.setSize(800,400);
        configPanel.setLayout(new GridLayout(4,1));

        for (int i=0; i<3; i++){
            HorseConfigPanel hcp=new HorseConfigPanel();
            configPanel.add(hcp.getHorsePanel());
        }
        JScrollPane horseConfigScroll= new JScrollPane(configPanel);
        return horseConfigScroll;
    }
    
    private static JPanel createRaceSettingsPanel() {
        JSpinner raceLengthSpinner = createRaceLengthSpinner();
        JSpinner lanesCountSpinner = createLaneCountSpinner();
        JComboBox laneTypeList = createLaneTypeList();

        JComboBox weatherList = createWeatherList(new Weather[] {Weather.SUNNY,Weather.RAINY,Weather.SNOWY});

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setSize(300,200);
    
        panel.add(new JLabel("Race Length:"));
        panel.add(raceLengthSpinner);
    
        panel.add(new JLabel("Lanes:"));
        panel.add(lanesCountSpinner);

        panel.add(new JLabel("Lane Type:"));
        panel.add(laneTypeList);

        panel.add(new JLabel("Weather:"));
        panel.add(weatherList);
    
        panel.add(new JLabel("Start Race:"));

        JButton startButton = new JButton("Confirm");
        startButton.addActionListener(e -> createRace((int) raceLengthSpinner.getValue(),(int) lanesCountSpinner.getValue(), (LaneType)laneTypeList.getSelectedItem(),(Weather)weatherList.getSelectedItem()));
        panel.add(startButton);
    
        return panel;
    }

    private static JSpinner createRaceLengthSpinner(){
        SpinnerNumberModel raceLengthModel = new SpinnerNumberModel(10, 10, 50, 1);
        return new JSpinner(raceLengthModel);
    }

    private static JSpinner createLaneCountSpinner(){
        SpinnerNumberModel lanesCountModel = new SpinnerNumberModel(3, 3, 12, 1);
        return new JSpinner(lanesCountModel);
    }

    private static JComboBox createLaneTypeList(){
        JComboBox laneTypeList = new JComboBox(LaneType.values());
        laneTypeList.setSelectedIndex(0);
        return laneTypeList;
    }

    private static JComboBox createWeatherList(Object[] weatherValues){
        JComboBox weatherList = new JComboBox(weatherValues);
        weatherList.setSelectedIndex(0);
        return weatherList;
    }
    

    private static void createRace(int raceLength, int laneCount, LaneType laneType, Weather weather) {
        Race race= new Race(raceLength,laneCount,laneType,weather);
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6,4.0,20, Breed.ARABIAN));
        race.addHorse(new Horse('♞', "KOKOMO", 0.5 , 3.0, 20, Breed.QUARTER_HORSE));
        race.addHorse(new Horse('♛', "EL JEFE", 0.4, 7.0, 20,Breed.CLYDESDALE));

        settingsFrame.dispose();

        switch(race.getLaneType()){
            case LaneType.STRAIGHT:
                gui=new StraightRaceGUI(race);
                break;
            case LaneType.OVAL:
                gui=new OvalRaceGUI(race);
                break;
        }

        gui.createFrame();
        race.startRace();
    }

    public static void raceEnd(ArrayList<Horse> winners){
        if(gui!=null){
            gui.raceEnd(winners);
        }
    }
}
