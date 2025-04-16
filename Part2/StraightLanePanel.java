import java.awt.*;

public class StraightLanePanel extends LanePanel {

    public StraightLanePanel(Horse horse, int raceLength) {
        super(horse, raceLength);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawRect(0,0, (raceLength*25+25)-1, getHeight()-1); //borders of the lane
        g.drawLine(raceLength*25,0,raceLength*25,25);

        if(this.horse!= null){
            g.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g.drawString(this.symbol+"", horse.getDistanceTravelled()*25, 17); //examine

            //g.setFont(new Font("SansSerif", Font.PLAIN, 10));
            //g.drawString(horse.getName()+" (Current confidence "+horse.getConfidence()+")",25*raceLength+35, 15);
        }
        
    }

}
