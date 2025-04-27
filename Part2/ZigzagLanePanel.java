import java.awt.*;

public class ZigzagLanePanel extends LanePanel{

    private int segmentLength;
    private final int SEGMENT_COUNT;

    private boolean isSlowed;

    private RaceGUI gui;

    public ZigzagLanePanel(Horse horse, int raceLength,RaceGUI gui) {
        super(horse, raceLength);
        this.SEGMENT_COUNT=3;

        this.segmentLength=raceLength*25/SEGMENT_COUNT;

        this.gui=gui;
        this.isSlowed=false;
        setOpaque(false);
    }

    public ZigzagLanePanel(Horse horse, int raceLength) {
        super(horse, raceLength);
        this.SEGMENT_COUNT=3;

        this.segmentLength=raceLength*25/SEGMENT_COUNT;

        this.isSlowed=false;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        drawZigzag(g, 0);
        drawZigzag(g, 25);

        if(this.horse!= null){
            g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            g.setColor(horse.getCoatColor());
            g.drawString(this.symbol+"",getHorseX()-1,this.horse.getDistanceTravelled()*25 -1);
        }
    }

    private void drawZigzag(Graphics g, int base){
        for(int i=0; i<SEGMENT_COUNT;i++){
            int endX=0;
            int startX = base;
            if(i%2==0){ // '\'
                endX=startX+segmentLength;
            }
            else{ // '/'
                endX=startX;
                startX+=segmentLength;
            }
            g.drawLine(startX, i*segmentLength,endX, (i+1) * segmentLength);
        }
    }

    private int getHorseX(){
        int segmentIndex = horse.getDistanceTravelled()*25/segmentLength; // current segment
        double segmentProgress = (horse.getDistanceTravelled()*25% segmentLength) /(double)segmentLength;

        boolean isAtIntersection=(segmentProgress<0.2 && segmentIndex!=0) || (segmentProgress>=0.8 && segmentIndex!=SEGMENT_COUNT-1);
        if(isAtIntersection && !isSlowed){ //intersection AND not slowed yet
            this.isSlowed=true;
            this.gui.slowDownTimer(this);
        }
        else if(!isAtIntersection && isSlowed){ //no longer at intersection but have been slowed
            this.isSlowed=false;
            this.gui.resetTimer(this);
        }

        if(segmentIndex%2 ==0){
            return (int)((segmentLength*segmentProgress));
        }
        else{
            return (int)(segmentLength-(segmentLength*segmentProgress));
        }
    }
}