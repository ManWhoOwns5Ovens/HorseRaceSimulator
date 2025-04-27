import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class StraightRaceGUI extends RaceGUI{
    public StraightRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        raceWidth=raceLength*25;
        raceHeight=(laneCount+1)*25;

        raceFrame.setSize(raceWidth+375, raceHeight+200);

        JPanel racePanel= new JPanel();
        racePanel.setLayout(new GridBagLayout());
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth + 350, raceHeight);

        ArrayList<Horse> horses=race.getHorses();
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;

        for(int i = 0; i < horses.size(); i++){
            Horse theHorse= horses.get(i);

            gbc.gridx=0;
            gbc.gridy=i;
            gbc.weightx=1.0;
            gbc.weighty = 1.0;
            StraightLanePanel newHorseLane= new StraightLanePanel(theHorse, raceLength);
            newHorseLane.setPreferredSize(new Dimension(raceWidth+25, 15)); 
            racePanel.add(newHorseLane,gbc);

            gbc.gridx = 1;
            racePanel.add(createHorseLabel(theHorse),gbc);
            lanes.add(newHorseLane);
        }

        for (int i = horses.size(); i < laneCount; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            StraightLanePanel emptyLane= new StraightLanePanel(null, raceLength);
            emptyLane.setPreferredSize(new Dimension(raceWidth+25, 15));
            racePanel.add(emptyLane, gbc);
    
            gbc.gridx = 1;
            racePanel.add(new JLabel(""), gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = laneCount;
        gbc.gridwidth = 2;
        JLabel weatherLabel = new JLabel("Weather: " + race.getWeather().toString());
        weatherLabel.setForeground(race.getWeather().getColor());
        racePanel.add(weatherLabel, gbc);

        raceFrame.add(racePanel);
        raceFrame.setVisible(true);
        startAllTimers();
    }

    @Override
    protected JPanel setResultsPanelLocation(JPanel rp){
        rp.setLocation(25, raceHeight+50);
        return rp;
    }

}