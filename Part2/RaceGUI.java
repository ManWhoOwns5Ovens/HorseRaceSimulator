import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

abstract class RaceGUI {
    // unique variables to be shared across the class
    // methods are separated for readability and maintainability
    protected  JFrame raceFrame;

    protected Race race;
    protected int raceLength;
    protected int laneCount;    
    
    protected int raceWidth;
    protected int raceHeight;

    protected Timer timer;

    protected ArrayList<LanePanel> lanes;

    public RaceGUI(Race race){
        this.race=race;
        this.raceLength=race.getRaceLength();
        this.laneCount=race.getLaneCount();
        this.raceFrame=new JFrame("Race");
        this.raceWidth=0;
        this.raceHeight=0;
        this.lanes=new ArrayList();
    }

    public void createFrame(){
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setLayout(null);
        
        createRacePanel();
    }

    protected void createRacePanel(){}

    protected void startTimer(){
        timer = new Timer(100+race.getWeather().getSpeedModifier(), e -> {
            for(LanePanel lane: lanes){
                lane.updateLane();
            }
        });
        timer.start();
    }

    public void raceEnd(ArrayList<Horse> winners){
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

    protected JPanel createResultsPanel(String displayMessage){
        JPanel resultsPanel= new JPanel();
        resultsPanel.setLayout(new GridBagLayout());
        resultsPanel.setSize(raceWidth+200, 100);

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

    protected JLabel createResultsFinalMessage(String displayMessage){
        JLabel messageLabel=new JLabel(displayMessage);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        messageLabel.setForeground(Color.BLACK); 
        return messageLabel;
    }

    protected JLabel createResultsQuestionMessage(){
        JLabel questionLabel=new JLabel("Would you like to run another race?");
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        questionLabel.setForeground(Color.BLACK); 
        return questionLabel;
    }

    protected JButton createYesButton(){
        JButton yesButton= new JButton("Yes");
        yesButton.addActionListener(e->{
            raceFrame.dispose();
            SwingUtilities.invokeLater(() -> App.main(null));
        });
        return yesButton;
    }

    protected JButton createNoButton(){
        JButton noButton= new JButton("No");
        noButton.addActionListener(e->{
            raceFrame.dispose();
            System.exit(0);// close frame, end program
        });
        return noButton;
    }

    protected String decideFinalMessage(ArrayList<Horse> winners){
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

