import java.awt.*;
import javax.swing.*;

public class GUI {
    public static void createFrame(Race race){
        int length=race.getRaceLength();
        int lanes=race.getLaneCount();

        JFrame raceFrame= new JFrame("Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setSize(length*25+100, lanes*25+100);
        raceFrame.setLayout(null);

        LanePanel lanePanel1= new LanePanel(race.getLane1Horse());
        lanePanel1.setSize(25*length+25, 25);
        lanePanel1.setLocation(25, 50+25*0);
        raceFrame.add(lanePanel1);

        LanePanel lanePanel2= new LanePanel(race.getLane2Horse());
        lanePanel2.setSize(25*length+25, 25);
        lanePanel2.setLocation(25, 50+25*1);
        raceFrame.add(lanePanel2);

        LanePanel lanePanel3= new LanePanel(race.getLane3Horse());
        lanePanel3.setSize(25*length+25, 25);
        lanePanel3.setLocation(25, 50+25*2);
        raceFrame.add(lanePanel3);

        for(int i=0; i<lanes-3;i++){
            JPanel emptyLane= new JPanel();
            emptyLane.setSize(25*length+25, 25);
            emptyLane.setLocation(25, 25+(i+3)*25);
            emptyLane.setBackground(Color.GREEN);
            raceFrame.add(emptyLane);
        }

        raceFrame.setVisible(true);

        Timer timer = new Timer(300, e -> {
            lanePanel1.updateLane();
            lanePanel2.updateLane();
            lanePanel3.updateLane();
        });
        timer.start();
    }
}
