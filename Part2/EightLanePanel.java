import java.awt.*;
 class EightLanePanel extends LanePanel{

    private int offset,laneNumber;
    private int diameter, radius, circleDistance;

    private boolean isFirst,isLast;
    private boolean isSlowed;

    private RaceGUI gui;

    public EightLanePanel(Horse horse, int raceLength,int offset,int laneNumber, RaceGUI gui) {
        super(horse, raceLength);
        this.offset=offset;
        this.laneNumber=laneNumber;

        this.diameter=(raceLength*25 + laneNumber*50)/2;
        this.radius=this.diameter/2;
        this.circleDistance=(raceLength*25)/2 + (25-raceLength)*3;

        this.isFirst=(laneNumber==0);
        this.isLast=(offset==1);

        this.gui=gui;
        this.isSlowed=false;

        setOpaque(false);
    }

    public EightLanePanel(Horse horse, int raceLength,int offset,int laneNumber) {
        super(horse, raceLength);
        this.offset=offset;
        this.laneNumber=laneNumber;

        this.diameter=(raceLength*25 + laneNumber*50)/2;
        this.radius=this.diameter/2;
        this.circleDistance=(raceLength*25)/2 + (25-raceLength)*3;//prevent right arc from being silly

        this.isFirst=(laneNumber==0);
        this.isLast=(offset==1);

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(this.isFirst){
            int lengthModifier=offset*13 + radius+((10-raceLength)*3);//scaling with race length
            int laneModifier=((offset-2)*15);//scaling with lane number
            g.drawOval(lengthModifier,lengthModifier,radius-1,radius-1);
            g.drawOval(lengthModifier+circleDistance+laneModifier,lengthModifier,radius-1,radius-1);
        }

        if (this.isLast) {
            int laneModifier=((laneNumber-1)*15);//scaling with lane number
            g.drawArc(25*offset,25*offset,diameter+50-1,diameter+50-1, 40, 280);
            g.drawArc(25*offset+circleDistance+laneModifier,25*offset,diameter+50-1,diameter+50-1, -140, 280);
        }   

        if(this.horse!= null){
            g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            g.setColor(horse.getCoatColor());
            g.drawString(this.symbol+"",getHorseX()+10,getHorseY()-10);
        }
    }

    private int getHorseX(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;

        //in the middle of the track/ at the intersection
        //only change timers when not already slowed
        boolean isAtIntersection=(progress>=0.125 && progress<0.375) || (progress>=0.625 && progress<0.875);
        if(isAtIntersection && !isSlowed){ //intersection AND not slowed yet
            this.isSlowed=true;
            this.gui.slowDownTimer(this);
        }
        else if(!isAtIntersection && isSlowed){ //no longer at intersection but have been slowed
            this.isSlowed=false;
            this.gui.resetTimer(this);
        }

        double angle = 2 * Math.PI *progress;

        double xPos= (2 / (3 - Math.cos(2 * angle))) * //scale
        Math.cos(angle);

        return (int)(25*offset+diameter+ (diameter -10) *xPos);
    }

    private int getHorseY(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle = 2 * Math.PI *progress;

        double yPos =(2 / (3 - Math.cos(2 * angle))) * //scale
        Math.sin(2 * angle) / 2;

        return (int)(25* offset+ radius+ (diameter+20)* yPos);
    }


}