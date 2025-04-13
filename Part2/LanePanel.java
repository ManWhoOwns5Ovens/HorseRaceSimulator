import java.awt.*;
import javax.swing.*;

public class LanePanel extends JPanel {
    protected  Horse horse;
    protected char symbol;
    protected int raceLength;

    public LanePanel(Horse horse, int raceLength) {
        this.horse = horse;
        setSymbol();
        this.raceLength=raceLength;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void updateLane(){
        setSymbol();
        repaint();
    }

    protected void setSymbol(){
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
