import java.awt.*;

public class OvalLanePanel extends LanePanel {

    public OvalLanePanel(Horse horse, int raceLength) {
        super(horse, raceLength);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);


        if(this.horse!= null){
            g.setFont(new Font("SansSerif", Font.PLAIN, 15));

            g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        }
        
    }

}
