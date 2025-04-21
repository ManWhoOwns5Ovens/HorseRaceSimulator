import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceSettingsGUI {
    private static JFrame settingsFrame;
    private static JPanel horseConfigPanel;
    private static RaceGUI gui;

    private static ArrayList<HorseConfigPanel> horsePanels= new ArrayList<>();

    private static ArrayList<Horse> horses = new ArrayList<>();

    private static JSpinner lanesCountSpinner;//needs to be accessed and used when adding horses

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
        horseConfigPanel= new JPanel();
        horseConfigPanel.setSize(800,400);
        horseConfigPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.weighty=1.0;
        gbc.ipady=10;

        int limit = 3;

        if(!horses.isEmpty()){limit=horsePanels.size();}

        for (int i=0; i<limit; i++){
            HorseConfigPanel hcp=new HorseConfigPanel();
            if(horses.size()>i && horses.get(i)!=null){hcp=new HorseConfigPanel(horses.get(i));}//load values if respective horses exists

            gbc.gridy=i;
            horseConfigPanel.add(hcp,gbc); // add to GUI
            horsePanels.add(hcp); // keep track off object
        }
        
        if(horsePanels.size()<12){
            gbc.gridy=horsePanels.size();
            JButton addHorseButton=createAddHorseButton();
            horseConfigPanel.add(addHorseButton,gbc);
        }
                
        JScrollPane horseConfigScroll= new JScrollPane(horseConfigPanel);
        return horseConfigScroll;
    }

    private static void addHorseConfigPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

        if (horseConfigPanel.getComponentCount() > 0) {
            horseConfigPanel.remove(horseConfigPanel.getComponentCount() - 1);
        }//remove latest element(always button)

        HorseConfigPanel newHorsePanel=new HorseConfigPanel();
        if(horses.size()>horsePanels.size()){newHorsePanel=new HorseConfigPanel(horses.get(horsePanels.size()));}//load data if horse existed already

        gbc.gridy=horsePanels.size();
        horseConfigPanel.add(newHorsePanel,gbc); // add to GUI
        horsePanels.add(newHorsePanel); // keep track off object

        if(horsePanels.size()<12){
            gbc.gridy++;
            JButton addHorseButton=createAddHorseButton();
            horseConfigPanel.add(addHorseButton,gbc);
        }
        
        horseConfigPanel.revalidate();
        horseConfigPanel.repaint();
    }

    private static JButton createAddHorseButton(){
        JButton addHorseButton=new JButton("+");
        addHorseButton.addActionListener(e -> {
            addHorseConfigPanel();

            lanesCountSpinner.setModel(new SpinnerNumberModel(horsePanels.size(),horsePanels.size(),12,1));
            settingsFrame.revalidate();
            settingsFrame.repaint();
    });
        return addHorseButton;
    }
    
    private static JPanel createRaceSettingsPanel() {
        JSpinner raceLengthSpinner = createRaceLengthSpinner();
        lanesCountSpinner = createLaneCountSpinner();
        JComboBox laneTypeList = createLaneTypeList();

        JComboBox weatherList = createWeatherList(new Weather[] {Weather.SUNNY,Weather.RAINY,Weather.SNOWY});

        JPanel panel = new JPanel(new GridLayout(5, 2));
    
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

        if(horses.isEmpty()){
            for(int i=0; i<horsePanels.size();i++){
                HorseConfigPanel info=horsePanels.get(i);
                Horse newHorse=new Horse(info.getSymbol(),info.getName(),0.5,info.getBreed(),info.getSaddle(),info.getHorseshoes(),
                info.getAccessory(),info.getColour());

                race.addHorse(newHorse);
                horses.add(newHorse);
            }
        }
        else{
            for(int i=0; i<horses.size();i++){
                HorseConfigPanel info=horsePanels.get(i);
                Horse newHorse=new Horse(info.getSymbol(),info.getName(),horses.get(i).getConfidence(),info.getBreed(),info.getSaddle(),info.getHorseshoes(),
                info.getAccessory(),info.getColour());

                race.addHorse(newHorse);
                horses.set(i,newHorse);//replace old version of horse
            }
        }
        
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
