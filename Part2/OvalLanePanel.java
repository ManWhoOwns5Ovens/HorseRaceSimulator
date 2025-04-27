import java.awt.*;

public class OvalLanePanel extends LanePanel {
    private int offset;
    private int width,height;

    public OvalLanePanel(Horse horse, int raceLength,int offset,int laneNumber) {
        super(horse, raceLength);
        this.offset=offset;

        this.width=raceLength*25 + laneNumber*50;
        this.height=raceLength*25/2 + laneNumber*50;

        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawOval(25*offset,25*offset,width-1,height-1);
        g.drawOval(25*offset-25,25*offset-25,width+50-1,height+50-1);
        g.drawLine(25*offset+width,25*offset+height/2,25*offset+width+50,25*offset+height/2);
        
        if(this.horse!= null){
            g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            g.setColor(horse.getCoatColor());
            g.drawString(this.symbol+"",getHorseX()-10,getHorseY()+5);
        }
    }

    private int getHorseX(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle=2 * Math.PI * progress;
        int radius= width/2;

        return (int)(25*offset+radius+(radius+5)*Math.cos(angle));
    }

    private int getHorseY(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle=2 * Math.PI * progress;
        int radius= height/2;

        return (int)(25*offset+radius+ (radius+5) * Math.sin(angle));
    }

}
