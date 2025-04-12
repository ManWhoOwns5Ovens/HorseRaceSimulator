import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceGUI {
    // unique variables to be shared across the class
    // methods are separated for readability and maintainability
    private static JFrame raceFrame;
    private static int length;
    private static int lanes;
    private static Timer timer;

    public static void createFrame(Race race){
        length=race.getRaceLength();
        lanes=race.getLaneCount();

        raceFrame= new JFrame("Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setSize(length*25+100 + 250, (lanes+1)*25+200);
        raceFrame.setLayout(null);
        
        displayRace(race);
    }

    private static void displayRace(Race race){
        JPanel racePanel= new JPanel();
        racePanel.setLayout(new GridLayout(lanes,1));
        racePanel.setLocation(25,50);
        racePanel.setSize(25*length+25 + 250, 25*lanes);

        LanePanel lanePanel1= new LanePanel(race.getLane1Horse(), length);
        racePanel.add(lanePanel1);

        LanePanel lanePanel2= new LanePanel(race.getLane2Horse(), length);
        racePanel.add(lanePanel2);

        LanePanel lanePanel3= new LanePanel(race.getLane3Horse(), length);
        racePanel.add(lanePanel3);

        for(int i=0; i<lanes-3;i++){
            LanePanel emptyLane= new LanePanel(null, length);
            racePanel.add(emptyLane);
        }

        raceFrame.add(racePanel);
        raceFrame.setVisible(true);

        timer = new Timer(300, e -> {
            lanePanel1.updateLane();
            lanePanel2.updateLane();
            lanePanel3.updateLane();
        });
        timer.start();
    }

    public static void raceEnd(ArrayList<Horse> winners){
        timer.stop();
        String displayMessage="";
        if(winners.size()>=2){
            displayMessage="It's a tie between ";

            for(int i=0; i<winners.size();i++){
                if(i!=0){displayMessage=displayMessage+" and ";}// add AND in front if not the first horse
                displayMessage=displayMessage+(winners.get(i).getName());
            }
        }
        else if(winners.size()==1){
            displayMessage="And the winner is... "+winners.get(0).getName()+"!";//Prints race winner;
        }
        else{
            displayMessage="All horses have fallen! Race can no longer continue";
        }

        JPanel resultsPanel= new JPanel();
        resultsPanel.setLayout(new GridBagLayout());
        resultsPanel.setSize(25*length+250, 100);
        resultsPanel.setLocation(25, 50+25*lanes);

        GridBagConstraints gbc= new GridBagConstraints();

        JLabel messageLabel=new JLabel(displayMessage);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        messageLabel.setForeground(Color.BLACK); 
        //
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        resultsPanel.add(messageLabel,gbc);

        JLabel questionLabel=new JLabel("Would you like to run another race?");
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        questionLabel.setForeground(Color.BLACK); 
        //
        gbc.gridy=1;
        resultsPanel.add(questionLabel,gbc);

        JButton yesButton= new JButton("Yes");
        yesButton.addActionListener(e->yesButton());
        //
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.weightx=0.5;
        gbc.anchor = GridBagConstraints.CENTER; 
        resultsPanel.add(yesButton,gbc);

        JButton noButton= new JButton("No");
        noButton.addActionListener(e->noButton());
        //
        gbc.gridx=1;
        resultsPanel.add(noButton,gbc);

        raceFrame.add(resultsPanel);
        raceFrame.revalidate();
        raceFrame.repaint();
        raceFrame.setVisible(true);
    }

    public static void yesButton(){ //start another race
        raceFrame.dispose();
        SwingUtilities.invokeLater(() -> App.main(null));
    }

    public static void noButton(){ //end program
        raceFrame.dispose();
        System.exit(0);// close frame, end program
    }
}

