import java.awt.*;
import javax.swing.*;

public class LanePanel extends JPanel {
    private Horse horse;
    private char symbol;
    private int raceLength;

    public LanePanel(Horse horse, int raceLength) {
        this.horse = horse;
        setSymbol();
        this.raceLength=raceLength;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawRect(0,0, (raceLength*25+25)-1, getHeight()-1); //borders of the lane
        //g.drawRect(raceLength*25,0, 25, getHeight()-1); //draw borders of last spot/ finish line
        g.drawLine(raceLength*25,0,raceLength*25,25);

        if(this.horse!= null){
            g.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g.drawString(this.symbol+"", horse.getDistanceTravelled()*25, 17); //examine

            g.setFont(new Font("SansSerif", Font.PLAIN, 10));
            g.drawString(horse.getName()+" (Current confidence "+horse.getConfidence()+")",25*raceLength+35, 15);
        }
        
    }

    public void updateLane(){
        setSymbol();
        repaint();
    }

    private void setSymbol(){
        if(this.horse!=null){
            if(this.horse.hasFallen()){
                this.symbol='❌';
            }
            else{
                this.symbol=horse.getSymbol();
            }
        }
        else{
            this.symbol=' ';
        }
    }

}
