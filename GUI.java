import javax.swing.*;

public class GUI {
    // unique variables to be shared across the class
    // methods are separated for readability and maintainability
    private static JFrame raceFrame;
    private static int length;
    private static int lanes;
    private static Race race;

    public static void createFrame(Race newRace){
        race=newRace;
        length=race.getRaceLength();
        lanes=race.getLaneCount();

        raceFrame= new JFrame("Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setSize(length*25+100 + 250, lanes*25+125);
        raceFrame.setLayout(null);
        
        displayRace();
    }

    public static void displayRace(){
        LanePanel lanePanel1= new LanePanel(race.getLane1Horse(), length);
        lanePanel1.setLocation(25, 50+25*0);
        lanePanel1.setSize(25*length+25 + 250, 25);
        raceFrame.add(lanePanel1);

        LanePanel lanePanel2= new LanePanel(race.getLane2Horse(), length);
        lanePanel2.setLocation(25, 50+25*1);
        lanePanel2.setSize(25*length+25 +250, 25);
        raceFrame.add(lanePanel2);

        LanePanel lanePanel3= new LanePanel(race.getLane3Horse(), length);
        lanePanel3.setLocation(25, 50+25*2);
        lanePanel3.setSize(25*length+25 +250, 25);
        raceFrame.add(lanePanel3);

        for(int i=0; i<lanes-3;i++){
            LanePanel emptyLane= new LanePanel(null, length);
            emptyLane.setSize(25*length+25 +250, 25);
            emptyLane.setLocation(25, 50+(i+3)*25);
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
