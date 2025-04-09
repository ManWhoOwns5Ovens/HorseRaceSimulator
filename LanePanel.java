import java.awt.*;
import javax.swing.*;

public class LanePanel extends JPanel {
    private Horse horse;
    private char symbol;

    public LanePanel(Horse horse){
        this.horse = horse;
        this.symbol = horse.getSymbol();
        this.setBackground(Color.GREEN);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("SansSerif", Font.PLAIN, 15));
        g.drawString(this.symbol+"", horse.getDistanceTravelled()*25, 12); //examine
    }

    public void updateLane(){
        if(horse.hasFallen()){
            this.symbol='❌';
        }
        repaint();
    }

}
