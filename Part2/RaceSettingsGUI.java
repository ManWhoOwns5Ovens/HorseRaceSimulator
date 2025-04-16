import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class RaceSettingsGUI {
    private static JFrame settingsFrame;
    private static RaceGUI gui;

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
        settingsFrame.add(createHorseConfig(), gbc); 
    
        gbc.gridx = 1;
        gbc.weightx = 0.33;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsFrame.add(raceSettingsPanel, gbc);
    
        settingsFrame.setVisible(true);
    }

    private static JScrollPane createHorseConfig(){
        JPanel configPanel= new JPanel();
        configPanel.setLayout(new GridLayout(13,1));

        for (int i=0; i<3; i++){
            JPanel horseConfigPanel=new JPanel();
            horseConfigPanel.setLayout(new GridBagLayout());
        }

        JScrollPane horseConfigScroll= new JScrollPane(configPanel);
        return horseConfigScroll;
    }
    
    private static JPanel createRaceSettingsPanel() {
        JSpinner raceLengthSpinner = createRaceLengthSpinner();
        JSpinner lanesCountSpinner = createLaneCountSpinner();
        JComboBox laneTypeList = createLaneTypeList();

        final HashMap<String,Weather> weatherMap = new HashMap<>();
        weatherMap.put("Sunny", new Weather( 0, 1.0,"Sunny", Color.YELLOW));
        weatherMap.put("Rainy", new Weather( -1.0, 1.0,"Rainy", Color.CYAN));
        weatherMap.put("Snowy", new Weather( 0, 2.0,"Snowy", Color.WHITE));
        JComboBox weatherList = createWeatherList(weatherMap.keySet().toArray());

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
        startButton.addActionListener(e -> createRace((int) raceLengthSpinner.getValue(),(int) lanesCountSpinner.getValue(), (LaneType)laneTypeList.getSelectedItem(),(Weather)weatherMap.get(weatherList.getSelectedItem())));
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
        weatherList.setSelectedIndex(2);
        return weatherList;
    }
    

    private static void createRace(int raceLength, int laneCount, LaneType laneType, Weather weather) {
        Race race= new Race(raceLength,laneCount,laneType,weather);
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6,4.0,20));
        race.addHorse(new Horse('♞', "KOKOMO", 0.5 , 3.0, 20));
        race.addHorse(new Horse('♛', "EL JEFE", 0.4, 5.0, 20));

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
