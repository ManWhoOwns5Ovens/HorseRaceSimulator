import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class EightRaceGUI extends RaceGUI{
    public EightRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        raceWidth=(raceLength*25 + laneCount*50)*2;
        raceHeight=raceLength*25 + laneCount*50;
        raceFrame.setSize(raceWidth+150, raceHeight+400);

        JPanel racePanel= new JPanel();

        racePanel.setLayout(null);
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth+100,raceHeight+100);

        ArrayList<Horse> horses=race.getHorses();
        for(int i=0; i<horses.size();i++){
            EightLanePanel newHorseLane= new EightLanePanel(horses.get(i), raceLength,(laneCount-i),i,this);
            newHorseLane.setSize(raceWidth,raceHeight);
            newHorseLane.setLocation(25,50);
            racePanel.add(newHorseLane);
            lanes.add(newHorseLane);
        }

        for(int i=horses.size(); i<laneCount;i++){
            EightLanePanel emptyLane= new EightLanePanel(null, raceLength,(laneCount-i),i);
            emptyLane.setSize(raceWidth,raceHeight);
            emptyLane.setLocation(25,50);
            racePanel.add(emptyLane);
        }

        JLabel weatherLabel=new JLabel("Weather:"+race.getWeather().toString());
        weatherLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        weatherLabel.setSize(raceWidth+150, 25);
        weatherLabel.setLocation(25,25);
        
        racePanel.add(weatherLabel);
        raceFrame.add(racePanel);
        raceFrame.setVisible(true);
        startAllTimers();
    }
}
