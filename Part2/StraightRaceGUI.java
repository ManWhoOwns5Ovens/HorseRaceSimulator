import java.awt.*;
import javax.swing.*;

public class StraightRaceGUI extends RaceGUI{
    public StraightRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        JPanel racePanel= new JPanel();

        raceWidth=raceLength*25;
        raceHeight=(laneCount+1)*25;

        raceFrame.setSize(raceWidth+350, raceHeight+200);
        racePanel.setLayout(new GridLayout(laneCount+1,1));
        racePanel.setLocation(25,50);

        racePanel.setSize(raceWidth + 350, raceHeight);

        StraightLanePanel lanePanel1= new StraightLanePanel(race.getLane1Horse(), raceLength);
        racePanel.add(lanePanel1);
        lanes.add(lanePanel1);

        StraightLanePanel lanePanel2= new StraightLanePanel(race.getLane2Horse(), raceLength);
        racePanel.add(lanePanel2);
        lanes.add(lanePanel2);

        StraightLanePanel lanePanel3= new StraightLanePanel(race.getLane3Horse(), raceLength);
        racePanel.add(lanePanel3);
        lanes.add(lanePanel3);

        for(int i=3; i<laneCount;i++){
            StraightLanePanel emptyLane= new StraightLanePanel(null, raceLength);
            racePanel.add(emptyLane);
        }

        JLabel weatherLabel=new JLabel("Weather:"+race.getWeather().getName());
        racePanel.add(weatherLabel);

        raceFrame.add(racePanel);

        raceFrame.setVisible(true);
        startTimer();
    }
}