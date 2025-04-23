import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceSettingsGUI {
    private JFrame settingsFrame;
    private JPanel horseConfigPanel;
    private static RaceGUI gui;

    private ArrayList<HorseConfigPanel> horsePanels;

    private static ArrayList<Horse> horses;

    private JSpinner lanesCountSpinner;//needs to be accessed and used when adding horses

    public RaceSettingsGUI(){
        if(horses==null){
            horses= new ArrayList<>();
        }
        this.horsePanels= new ArrayList<>();
        this.createFrame();
    }

    public void createFrame() {
        settingsFrame = new JFrame("Race Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(1000, 400);
        settingsFrame.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;    
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        gbc.weighty=1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        settingsFrame.add(createHorseConfig(), gbc); 

        JPanel raceSettingsPanel = createRaceSettingsPanel();
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        settingsFrame.add(raceSettingsPanel, gbc);
    
        settingsFrame.setVisible(true);
    }

    //Horse Config
    private JScrollPane createHorseConfig(){
        horseConfigPanel= new JPanel();
        horseConfigPanel.setSize(800,400);
        horseConfigPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.weighty=1.0;
        gbc.ipady=10;
        gbc.gridwidth=2;

        int limit = 3;

        if(!horses.isEmpty()){limit=horses.size();}

        for (int i=0; i<limit; i++){
            HorseConfigPanel hcp=new HorseConfigPanel();
            if(!horses.isEmpty()){
                hcp=new HorseConfigPanel(horses.get(i));
            }

            gbc.gridy=i;
            horseConfigPanel.add(hcp,gbc); // add to GUI
            horsePanels.add(hcp); // keep track off object
        }
        
        gbc.gridy=horsePanels.size();
        horseConfigPanel.add(createBottomButtonPanel(), gbc);   
        JScrollPane horseConfigScroll= new JScrollPane(horseConfigPanel);
        return horseConfigScroll;
    }

    private void addHorseConfigPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weighty=1.0;
        gbc.ipady=10;

        while(horseConfigPanel.getComponentCount()>horsePanels.size()){
            horseConfigPanel.remove(horseConfigPanel.getComponentCount() - 1);
        }
        
        HorseConfigPanel newHorsePanel=new HorseConfigPanel();

        gbc.gridy=horsePanels.size();
        horseConfigPanel.add(newHorsePanel,gbc); // add to GUI
        horsePanels.add(newHorsePanel); // keep track off object

        gbc.gridy++;
        gbc.gridwidth=2;
        horseConfigPanel.add(createBottomButtonPanel(), gbc);

        horseConfigPanel.revalidate();
        horseConfigPanel.repaint();
    }

    private void removeHorseConfigPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

        while(horseConfigPanel.getComponentCount()>horsePanels.size()){
            horseConfigPanel.remove(horseConfigPanel.getComponentCount() - 1);
        }
        horseConfigPanel.remove(horseConfigPanel.getComponentCount() - 1);
        horsePanels.removeLast();
        if(!horses.isEmpty()){horses.removeLast();}

        gbc.gridy = horsePanels.size();
        gbc.gridwidth = 2;
        horseConfigPanel.add(createBottomButtonPanel(), gbc);

        horseConfigPanel.revalidate();
        horseConfigPanel.repaint();
    }

    private JButton createAddHorseButton(){
        JButton addHorseButton=new JButton("+");
        addHorseButton.addActionListener(e -> {
            addHorseConfigPanel();

            lanesCountSpinner.setModel(new SpinnerNumberModel(horsePanels.size(),horsePanels.size(),12,1));
            settingsFrame.revalidate();
            settingsFrame.repaint();
    });
        return addHorseButton;
    }

    private JButton createRemoveHorseButton(){
        JButton removeHorseButton=new JButton("-");
        removeHorseButton.addActionListener(e -> {
            removeHorseConfigPanel();

            lanesCountSpinner.setModel(new SpinnerNumberModel(horsePanels.size(),horsePanels.size(),12,1));
            settingsFrame.revalidate();
            settingsFrame.repaint();
    });
        return removeHorseButton;
    }

    private JPanel createBottomButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        if (horsePanels.size() < 12) {
            buttonPanel.add(createAddHorseButton());
        }
    
        if (horsePanels.size() > 3) {
            buttonPanel.add(createRemoveHorseButton());
        }
    
        return buttonPanel;
    }
    
    //Race Settings
    private JPanel createRaceSettingsPanel() {
        JSpinner raceLengthSpinner = createSpinner(10,50);

        lanesCountSpinner = createSpinner(horsePanels.size(),12);//horse config created before track settings hence allowing for direct use of horse panels size
        
        JComboBox laneTypeList = createComboBox(LaneType.values());

        JComboBox weatherList = createComboBox(new Weather[] {Weather.SUNNY,Weather.RAINY,Weather.SNOWY});

        JPanel trackSettingPanel = new JPanel(new GridLayout(5, 2));
    
        trackSettingPanel.add(new JLabel("Race Length:"));
        trackSettingPanel.add(raceLengthSpinner);
    
        trackSettingPanel.add(new JLabel("Lanes:"));
        trackSettingPanel.add(lanesCountSpinner);

        trackSettingPanel.add(new JLabel("Lane Type:"));
        trackSettingPanel.add(laneTypeList);

        trackSettingPanel.add(new JLabel("Weather:"));
        trackSettingPanel.add(weatherList);
    
        trackSettingPanel.add(new JLabel("Start Race:"));

        JButton startButton = new JButton("Confirm");
        startButton.addActionListener(e -> createRace((int) raceLengthSpinner.getValue(),(int) lanesCountSpinner.getValue(), (LaneType)laneTypeList.getSelectedItem(),(Weather)weatherList.getSelectedItem()));
        trackSettingPanel.add(startButton);
    
        return trackSettingPanel;
    }

    private JSpinner createSpinner(int defaultValue, int maxValue){
        SpinnerNumberModel lanesCountModel = new SpinnerNumberModel(defaultValue, defaultValue, maxValue, 1);
        return new JSpinner(lanesCountModel);
    }

    private JComboBox createComboBox(Object[] choices){
        JComboBox cb = new JComboBox(choices);
        cb.setSelectedIndex(0);
        return cb;
    }
    
    private void createRace(int raceLength, int laneCount, LaneType laneType, Weather weather) {
        Race race= new Race(raceLength,laneCount,laneType,weather);

        for(int i=1; i<=horsePanels.size();i++){
            HorseConfigPanel info=horsePanels.get(i-1);
            Horse newHorse = null;
            if(i>horses.size()){
                newHorse=new Horse(info.getSymbol(),info.getName(),0.5,info.getBreed(),info.getSaddle(),info.getHorseshoes(),
                info.getAccessory(),info.getColour());
                horses.add(newHorse);
            }
            else{
                newHorse=new Horse(info.getSymbol(),info.getName(),horses.get(i-1).getConfidence(),info.getBreed(),info.getSaddle(),info.getHorseshoes(),
                info.getAccessory(),info.getColour());
                horses.set(i-1,newHorse);//replace old version of horse
            }
            race.addHorse(newHorse);
        }
        
        settingsFrame.dispose();

        switch(race.getLaneType()){
            case LaneType.STRAIGHT:
                gui=new StraightRaceGUI(race);
                break;
            case LaneType.OVAL:
                gui=new OvalRaceGUI(race);
                break;
            case LaneType.EIGHT:
                gui=new EightRaceGUI(race);
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
