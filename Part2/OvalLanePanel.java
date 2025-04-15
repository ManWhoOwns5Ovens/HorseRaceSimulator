import java.awt.*;

public class OvalLanePanel extends LanePanel {

    private int offset;
    private int laneNumber;

    public OvalLanePanel(Horse horse, int raceLength,int offset,int laneNumber) {
        super(horse, raceLength);
        this.offset=offset;
        this.laneNumber=laneNumber;
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int width=raceLength*25 + (laneNumber-1)*50;
        int height=raceLength*25/2 + (laneNumber-1)*50;        
        
        g.drawOval(25*offset,25*offset,width-1,height-1);
        g.drawOval(25*offset-25,25*offset-25,width+50-1,height+50-1);
        
        if(this.horse!= null){
            g.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g.drawString(this.horse.getSymbol()+"",getHorseX(),getHorseY());
    
            g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        }
    }

    private int getHorseX(){
        double f=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle=2 * Math.PI * f;
        int radius= (raceLength*25 + (laneNumber-1)*50)/2;

        return (int)(25*offset+radius+radius*Math.cos(angle));
    }

    private int getHorseY(){
        double f=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle=2 * Math.PI * f;
        int radius= (raceLength*25/2 + (laneNumber-1)*50)/2;

        return (int)(25*offset+radius+ radius * Math.sin(angle));
    }

}
