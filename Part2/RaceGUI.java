import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RaceGUI {
    // unique variables to be shared across the class
    // methods are separated for readability and maintainability
    private static JFrame raceFrame;
    private static int length;
    private static int laneCount;
    private static Race race;
    
    private static int raceWidth;
    private static int raceHeight;

    private static Timer timer;

    private static ArrayList<LanePanel> lanes = new ArrayList<>();

    public static void createFrame(Race newRace){
        length=newRace.getRaceLength();
        laneCount=newRace.getLaneCount();
        race=newRace;

        raceFrame= new JFrame("Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setLayout(null);
        
        switch(race.getLaneType()){
            case LaneType.STRAIGHT:
                createStraightRacePanel();
                break;
            case LaneType.OVAL:
                createOvalRacePanel();
                break;
        }
    }

    private static void createOvalRacePanel(){
        JPanel racePanel= new JPanel();

        raceWidth=length*25 + laneCount*50;
        raceHeight=length*25/2 + laneCount*50;

        raceFrame.setSize(raceWidth+150, raceHeight+400);
        racePanel.setLayout(null);
        racePanel.setLocation(25,50);
        racePanel.setSize(raceWidth+100,raceHeight+100);

        OvalLanePanel lanePanel1= new OvalLanePanel(race.getLane1Horse(), length,(laneCount-(1-1)),1);
        lanePanel1.setSize(raceWidth,raceHeight);
        lanePanel1.setLocation(25,50);
        racePanel.add(lanePanel1);
        lanes.add(lanePanel1);

        OvalLanePanel lanePanel2= new OvalLanePanel(race.getLane2Horse(), length,(laneCount-(2-1)),2);
        lanePanel2.setSize(raceWidth,raceHeight);
        lanePanel2.setLocation(25,50);
        racePanel.add(lanePanel2);
        lanes.add(lanePanel2); 

        OvalLanePanel lanePanel3= new OvalLanePanel(race.getLane3Horse(), length,(laneCount-(3-1)),3);
        lanePanel3.setSize(raceWidth,raceHeight);
        lanePanel3.setLocation(25,50);
        racePanel.add(lanePanel3);
        lanes.add(lanePanel3); 

        for(int i=4; i<=laneCount;i++){
            OvalLanePanel emptyLane= new OvalLanePanel(null, length,(laneCount-(i-1)),i);
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

    private static void createStraightRacePanel(){
        JPanel racePanel= new JPanel();

        raceWidth=length*25;
        raceHeight=(laneCount+1)*25;

        raceFrame.setSize(raceWidth+350, raceHeight+200);
        racePanel.setLayout(new GridLayout(laneCount+1,1));
        racePanel.setLocation(25,50);

        racePanel.setSize(raceWidth + 250, raceHeight);

        StraightLanePanel lanePanel1= new StraightLanePanel(race.getLane1Horse(), length);
        racePanel.add(lanePanel1);
        lanes.add(lanePanel1);

        StraightLanePanel lanePanel2= new StraightLanePanel(race.getLane2Horse(), length);
        racePanel.add(lanePanel2);
        lanes.add(lanePanel2);

        StraightLanePanel lanePanel3= new StraightLanePanel(race.getLane3Horse(), length);
        racePanel.add(lanePanel3);
        lanes.add(lanePanel3);

        for(int i=0; i<laneCount-3;i++){
            StraightLanePanel emptyLane= new StraightLanePanel(null, length);
            racePanel.add(emptyLane);
        }

        JLabel weatherLabel=new JLabel("Weather:"+race.getWeather().getName());
        racePanel.add(weatherLabel);

        raceFrame.add(racePanel);

        raceFrame.setVisible(true);
        startTimer();
    }

    private static void startTimer(){
        timer = new Timer(300+race.getWeather().getSpeedModifier(), e -> {
            for(LanePanel lane: lanes){
                lane.updateLane();
            }
        });
        timer.start();
    }

    public static void raceEnd(ArrayList<Horse> winners){
        timer.stop();

        System.out.println("race ended");

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
        resultsPanel.setSize(raceWidth, 100);

        if(race.getLaneType()==LaneType.OVAL){
            resultsPanel.setLocation(25, raceHeight+150);
        }
        else{
            resultsPanel.setLocation(25, 50+raceHeight);
        }
        

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

