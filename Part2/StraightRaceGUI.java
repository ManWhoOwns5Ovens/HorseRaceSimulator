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
        racePanel.setLayout(new GridLayout(laneCount+1,2));
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth + 350, raceHeight);

        ArrayList<Horse> horses=race.getHorses();
        for(Horse theHorse : horses){
            StraightLanePanel newHorseLane= new StraightLanePanel(theHorse, raceLength);
            racePanel.add(newHorseLane);
            racePanel.add(new JLabel(theHorse.getName()+" (Current confidence "+theHorse.getConfidence()+")"));
            lanes.add(newHorseLane);
        }

        for(int i=3; i<laneCount;i++){
            StraightLanePanel emptyLane= new StraightLanePanel(null, raceLength);
            racePanel.add(emptyLane);
            racePanel.add(new JLabel(""));
        }

        JLabel weatherLabel=new JLabel("Weather:"+race.getWeather().getName());
        racePanel.add(weatherLabel);
        raceFrame.add(racePanel);
        raceFrame.setVisible(true);
        startTimer();
    }

}