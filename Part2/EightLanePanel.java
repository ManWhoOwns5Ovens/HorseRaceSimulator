import java.awt.*;
 class EightLanePanel extends LanePanel{

    private int offset;
    private int diameter, radius, circleDistance;

    private boolean isFirst,isLast;

    public EightLanePanel(Horse horse, int raceLength,int offset,int laneNumber) {
        super(horse, raceLength);
        this.offset=offset;

        this.diameter=(raceLength*25 + laneNumber*50)/2;
        this.radius=this.diameter/2;
        this.circleDistance=(int)(this.diameter);

        this.isFirst=(laneNumber==0);
        this.isLast=(offset==1);

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(this.isFirst){
            g.drawOval(25*offset,25*offset,diameter-1,diameter-1);
            g.drawOval(25*offset+(offset*50)+25,25*offset,diameter-1,diameter-1);
            g.drawString("X", 25*offset, 25*offset);
        }

        if (this.isLast) {
            g.drawOval(25*offset,25*offset,diameter+50-1,diameter+50-1);
            g.drawOval(25*offset+circleDistance,25*offset,diameter+50-1,diameter+50-1);
            g.drawString("J", 25*offset-25,25*offset-25);
        }   

        if(this.horse!= null){
            g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            g.setColor(horse.getCoatColor());
            g.drawString(this.symbol+"",getHorseX(),getHorseY());
        }
    }

    private int getHorseX(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle = 2 * Math.PI *progress;

        double xPos= (2 / (3 - Math.cos(2 * angle))) * //scale
        Math.cos(angle);

        return (int)(25*offset+diameter + (diameter+10)*xPos);

        /*if(progress<0.25){
            double angle=(progress/0.25)*Math.PI;
            return (int)(25*offset+radius+circleDistance
             + radius*Math.cos(angle));
        }
        else if(progress<0.75){
            double angle=((progress-0.25)/0.5) * 2 *Math.PI;
            return (int)(25*offset+radius
             + radius*Math.cos(angle));
        }
        else{
            double angle=((progress-0.75) / 0.25)*Math.PI + Math.PI;
            return (int)(25*offset+radius+circleDistance
             + radius*Math.cos(angle));
        }*/
    }

    private int getHorseY(){
        double progress=(double)this.horse.getDistanceTravelled() / (double)this.raceLength;
        double angle = 2 * Math.PI *progress;

        double yPos = (2 / (3 - Math.cos(2 * angle))) * //scale
        Math.sin(2 * angle) / 2;

        return (int)(25* offset+ radius + (diameter+10)* yPos);


        /*if(progress<0.25){
            angle=(progress/0.25)*Math.PI;
        }
        else if(progress<0.75){
            angle=((progress-0.25)/0.5) * 2 *Math.PI;
        }
        else{
            angle=((progress-0.75) / 0.25)*Math.PI + Math.PI;   
        }

        return (int)(25*offset+radius
             + radius*Math.sin(angle));*/
    }
}