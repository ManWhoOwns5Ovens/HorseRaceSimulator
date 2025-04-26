import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class EightRaceGUI extends RaceGUI{
    public EightRaceGUI(Race race){
        super(race);
    }

    @Override
    public void createRacePanel(){
        raceWidth=(raceLength*25 + laneCount*50) +50;
        raceHeight=raceLength*25 /2 + laneCount*50 ;
        raceFrame.setSize(raceWidth +300, raceHeight+250);

        JPanel racePanel= new JPanel();

        racePanel.setLayout(null);
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth+100,raceHeight+100);
        racePanel.setOpaque(false);

        ArrayList<Horse> horses=race.getHorses();

        JPanel horseLabelPanel= createHorseLabelPanel(horses);

        for(int i=0; i<horses.size();i++){
            Horse theHorse=horses.get(i);
            EightLanePanel newHorseLane= new EightLanePanel(theHorse, raceLength,(laneCount-i),i,this);
            newHorseLane.setSize(raceWidth,raceHeight);
            newHorseLane.setLocation(25,50);
            horseLabelPanel.add(createHorseLabel(theHorse));
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
        raceFrame.add(horseLabelPanel);
        raceFrame.setVisible(true);
        startAllTimers();
    }

    @Override
    protected JPanel setResultsPanelLocation(JPanel rp){
        rp.setLocation(0, raceHeight+75);
        return rp;
    }
}
