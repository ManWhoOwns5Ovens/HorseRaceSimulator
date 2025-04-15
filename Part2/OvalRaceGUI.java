import java.awt.*;
import javax.swing.*;


public class OvalRaceGUI extends RaceGUI{

    public OvalRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        JPanel racePanel= new JPanel();

        raceWidth=raceLength*25 + laneCount*50;
        raceHeight=raceLength*25/2 + laneCount*50;

        raceFrame.setSize(raceWidth+150, raceHeight+400);
        racePanel.setLayout(null);
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth+100,raceHeight+100);

        OvalLanePanel lanePanel1= new OvalLanePanel(race.getLane1Horse(), raceLength,(laneCount-0),0);
        lanePanel1.setSize(raceWidth,raceHeight);
        lanePanel1.setLocation(25,50);
        racePanel.add(lanePanel1);
        lanes.add(lanePanel1);

        OvalLanePanel lanePanel2= new OvalLanePanel(race.getLane2Horse(), raceLength,(laneCount-1),1);
        lanePanel2.setSize(raceWidth,raceHeight);
        lanePanel2.setLocation(25,50);
        racePanel.add(lanePanel2);
        lanes.add(lanePanel2); 

        OvalLanePanel lanePanel3= new OvalLanePanel(race.getLane3Horse(), raceLength,(laneCount-2),2);
        lanePanel3.setSize(raceWidth,raceHeight);
        lanePanel3.setLocation(25,50);
        racePanel.add(lanePanel3);
        lanes.add(lanePanel3); 

        for(int i=3; i<laneCount;i++){
            OvalLanePanel emptyLane= new OvalLanePanel(null, raceLength,(laneCount-i),i);
            emptyLane.setSize(raceWidth,raceHeight);
            emptyLane.setLocation(25,50);
            racePanel.add(emptyLane);
        }

        JLabel weatherLabel=new JLabel("Weather:"+race.getWeather().getName());
        weatherLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        weatherLabel.setSize(raceWidth+150, 25);
        weatherLabel.setLocation(25,25);
        racePanel.add(weatherLabel);

        raceFrame.add(racePanel);

        raceFrame.setVisible(true);
        startTimer();
    }
}