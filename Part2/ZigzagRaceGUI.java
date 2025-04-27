import java.util.ArrayList;
import javax.swing.*;

public class ZigzagRaceGUI extends RaceGUI{
    public ZigzagRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        raceWidth=raceLength*25;
        raceHeight=raceLength*25;

        raceFrame.setSize(raceWidth+375, raceHeight+200);

        JPanel racePanel= new JPanel();
        racePanel.setLayout(null);
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth, raceHeight);

        ArrayList<Horse> horses=race.getHorses();

        JPanel horseLabelPanel= createHorseLabelPanel(horses);

        for(int i = 0; i < horses.size(); i++){
            Horse theHorse= horses.get(i);

            ZigzagLanePanel newHorseLane= new ZigzagLanePanel(theHorse, raceLength,this);
            newHorseLane.setSize(raceWidth,raceHeight);
            newHorseLane.setLocation(i*25,0);
            racePanel.add(newHorseLane);

            horseLabelPanel.add(createHorseLabel(theHorse));
            lanes.add(newHorseLane);
        }

        for (int i = horses.size(); i < laneCount; i++) {

            ZigzagLanePanel emptyLane= new ZigzagLanePanel(null, raceLength);
            emptyLane.setSize(raceWidth,raceHeight);
            emptyLane.setLocation(i*25,0);
            racePanel.add(emptyLane);
    
            racePanel.add(new JLabel(""));
        }


        JLabel weatherLabel = new JLabel("Weather: " + race.getWeather().toString());
        weatherLabel.setForeground(race.getWeather().getColor());
        racePanel.add(weatherLabel);

        raceFrame.add(racePanel);
        raceFrame.add(horseLabelPanel);
        raceFrame.setVisible(true);
        startAllTimers();
    }

    @Override
    protected JPanel setResultsPanelLocation(JPanel rp){
        rp.setLocation(25, raceHeight+50);
        return rp;
    }
}