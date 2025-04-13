import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceGUI {
    // unique variables to be shared across the class
    // methods are separated for readability and maintainability
    private static JFrame raceFrame;
    private static int length;
    private static int laneCount;
    private static Timer timer;

    private static ArrayList<LanePanel> lanes = new ArrayList<>();

    public static void createFrame(Race race){
        length=race.getRaceLength();
        laneCount=race.getLaneCount();

        raceFrame= new JFrame("Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setSize(length*25+100 + 250, (laneCount+1)*25+200);
        raceFrame.setLayout(null);
        
        displayRace(race);
    }

    private static void displayRace(Race race){
        JPanel racePanel= new JPanel();
        racePanel.setLayout(new GridLayout(laneCount,1));
        racePanel.setLocation(25,50);
        racePanel.setSize(25*length+25 + 250, 25*laneCount);

        //
        StraightLanePanel lanePanel1= new StraightLanePanel(race.getLane1Horse(), length);
        lanePanel1.setBackground(race.getWeather().getColor());
        racePanel.add(lanePanel1);
        lanes.add(lanePanel1);

        StraightLanePanel lanePanel2= new StraightLanePanel(race.getLane2Horse(), length);
        lanePanel2.setBackground(race.getWeather().getColor());
        racePanel.add(lanePanel2);
        lanes.add(lanePanel2);

        StraightLanePanel lanePanel3= new StraightLanePanel(race.getLane3Horse(), length);
        lanePanel3.setBackground(race.getWeather().getColor());
        racePanel.add(lanePanel3);
        lanes.add(lanePanel3);
        //

        for(int i=0; i<laneCount-3;i++){
            LanePanel emptyLane= new LanePanel(null, length);
            racePanel.add(emptyLane);
        }

        raceFrame.add(racePanel);
        raceFrame.setVisible(true);

        timer = new Timer(300+race.getWeather().getSpeedModifier(), e -> {
            lanePanel1.updateLane();
            lanePanel2.updateLane();
            lanePanel3.updateLane();
        });
        timer.start();
    }

    public static void raceEnd(ArrayList<Horse> winners){
        timer.stop();

        if(winners.size()==0){
            for(LanePanel lane: lanes){
                lane.updateLane();
            }//make sure last horse is displayed as fallen - if all fallen
        }
        
        String displayMessage=decideFinalMessage(winners);
        
        JPanel resultsPanel= createResultsPanel(displayMessage);

        raceFrame.add(resultsPanel);
        raceFrame.revalidate();
        raceFrame.repaint();
        raceFrame.setVisible(true);
    }

    private static JPanel createResultsPanel(String displayMessage){
        JPanel resultsPanel= new JPanel();
        resultsPanel.setLayout(new GridBagLayout());
        resultsPanel.setSize(25*length+250, 100);
        resultsPanel.setLocation(25, 50+25*laneCount);

        GridBagConstraints gbc= new GridBagConstraints();

        JLabel messageLabel=createResultsFinalMessage(displayMessage);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        resultsPanel.add(messageLabel,gbc);

        JLabel questionLabel=createResultsQuestionMessage();
        gbc.gridy=1;
        resultsPanel.add(questionLabel,gbc);

        JButton yesButton= createYesButton();
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.weightx=0.5;
        gbc.anchor = GridBagConstraints.CENTER; 
        resultsPanel.add(yesButton,gbc);

        
        JButton noButton= createNoButton();
        gbc.gridx=1;
        resultsPanel.add(noButton,gbc);

        return resultsPanel;
    }

    private static JLabel createResultsFinalMessage(String displayMessage){
        JLabel messageLabel=new JLabel(displayMessage);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        messageLabel.setForeground(Color.BLACK); 
        return messageLabel;
    }

    private static JLabel createResultsQuestionMessage(){
        JLabel questionLabel=new JLabel("Would you like to run another race?");
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        questionLabel.setForeground(Color.BLACK); 
        return questionLabel;
    }

    private static JButton createYesButton(){
        JButton yesButton= new JButton("Yes");
        yesButton.addActionListener(e->{
            raceFrame.dispose();
            SwingUtilities.invokeLater(() -> App.main(null));
        });
        return yesButton;
    }

    private static JButton createNoButton(){
        JButton noButton= new JButton("No");
        noButton.addActionListener(e->{
            raceFrame.dispose();
            System.exit(0);// close frame, end program
        });
        return noButton;
    }

    private static String decideFinalMessage(ArrayList<Horse> winners){
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
        return displayMessage;
    }

}

