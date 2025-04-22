import java.awt.*;
import javax.swing.*;

public class HorseConfigPanel extends JPanel{
    private Horse theHorse;

    private JTextField nameInput;
    private JComboBox symbolInput;
    private Color colourInput;
    private JComboBox breedInput;
    private JComboBox saddleInput;
    private JComboBox horseshoesInput;
    private JComboBox accessoryInput;

    private JLabel finalConfidenceLabel;
    private JLabel finalSpeedLabel;
    private JLabel finalEnduraceLabel;

    public HorseConfigPanel(){
        this.setLayout(new GridBagLayout());
        this.createLabels();
        this.createNameInput();
        this.createComboBoxes();
        this.createStats();
    }

    public HorseConfigPanel(Horse theHorse){
        this.theHorse=theHorse;
        this.setLayout(new GridBagLayout());
        this.createLabels();
        this.createNameInput();
        this.createComboBoxes();
        this.createStats();
    }
    
    private void createNameInput(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        nameInput= new JTextField(10);
        if(this.theHorse!=null){nameInput.setText(theHorse.getName());}

        this.add(nameInput,gbc);
    }

    private void createLabels(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.ipadx=10;

        gbc.gridx=0;
        gbc.gridy=0;
        this.add(new JLabel("Name :"),gbc);
        gbc.gridy=1;
        this.add(new JLabel("Confidence :"),gbc);
        gbc.gridy=2;
        this.add(new JLabel("Speed :"),gbc); 
        gbc.gridy=3;
        this.add(new JLabel("Endurance :"),gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        this.add(new JLabel("Breed :"),gbc);
        gbc.gridy=1;
        this.add(new JLabel("Symbol :"),gbc);
        gbc.gridy=2;
        this.add(new JLabel("Colour :"),gbc);

        gbc.gridx=5;
        gbc.gridy=0;
        this.add(new JLabel("Saddle :"),gbc);
        gbc.gridy=1;
        this.add(new JLabel("Horseshoe :"),gbc);
        gbc.gridy=2;
        this.add(new JLabel("Accessory :"),gbc);
    }

    private void createStats(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridx=1;
        gbc.gridy=1;
        if(theHorse==null){this.add(new JLabel("0.5 -> "),gbc);}
        else{this.add(new JLabel(this.theHorse.getConfidence()+" -> "),gbc);}
        

        gbc.gridy=2;
        this.add(new JLabel("3.0 -> "),gbc);

        gbc.gridy=3;
        this.add(new JLabel("20 -> "),gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        finalConfidenceLabel=new JLabel("");
        this.add(finalConfidenceLabel,gbc);

        gbc.gridy=2;
        finalSpeedLabel=new JLabel("");
        this.add(finalSpeedLabel,gbc);

        gbc.gridy=3;
        finalEnduraceLabel=new JLabel("");
        this.add(finalEnduraceLabel,gbc);

        this.updateLabels();//calculates updates final label values to rounded values when created
    }

    private void createComboBoxes(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridx=4;
        gbc.gridy=0;
        breedInput=createComboBox(Breed.ALL_BREEDS);
        if(this.theHorse!=null){breedInput.setSelectedItem(this.theHorse.getBreed());}  
        this.add(breedInput,gbc);
        breedInput.addActionListener(e -> updateLabels());

        gbc.gridy=1;
        symbolInput=createComboBox(new String[]{"♘","♞","♔","♕","♖","♗", "♙","♚", "♛","♜","♝","♟"});
        symbolInput.setEditable(true);
        if(this.theHorse!=null){symbolInput.setSelectedItem(this.theHorse.getSymbol());}
        this.add(symbolInput,gbc);

        //Colours
        gbc.gridy=2;
        JButton colourPickerButton = new JButton("Pick Color");//shows/hides color chooser
        colourPickerButton.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(this, "Choose Horse Color", Color.BLACK);//returns selected color, opens color chooser UI in new window
            colourInput=selected;
        });
        if(this.theHorse!=null){colourInput=this.theHorse.getCoatColor();}
        this.add(colourPickerButton,gbc);

        //Saddles
        gbc.gridx=6;
        gbc.gridy=0;
        saddleInput=createComboBox(Saddle.ALL_SADDLES);
        if(this.theHorse!=null){saddleInput.setSelectedItem(this.theHorse.getSaddle());}      
        this.add(saddleInput,gbc);
        saddleInput.addActionListener(e -> updateLabels());

        //Horseshoes
        gbc.gridy=1;
        horseshoesInput=createComboBox(Horseshoes.ALL_HORSESHOES);
        if(this.theHorse!=null){horseshoesInput.setSelectedItem(this.theHorse.getHorseshoes());}  
        this.add(horseshoesInput,gbc);
        horseshoesInput.addActionListener(e -> updateLabels());

        //Accessories
        gbc.gridy=2;
        accessoryInput=createComboBox(Accessory.ALL_ACCESSORIES);
        if(this.theHorse!=null){accessoryInput.setSelectedItem(this.theHorse.getAccessory());}  
        this.add(accessoryInput,gbc);
        accessoryInput.addActionListener(e -> updateLabels());
    }

    private JComboBox createComboBox(Object[] choices){
        JComboBox newBox= new JComboBox(choices);
        newBox.setSelectedIndex(0);
        return newBox;
    }

    public String getName() {
        return nameInput.getText();
    }
    
    public char getSymbol() {
        return symbolInput.getSelectedItem().toString().charAt(0);
    }
    
    public Color getColour() {
        return colourInput;
    }
    
    public Breed getBreed() {
        return (Breed)breedInput.getSelectedItem();
    }
    
    public Saddle getSaddle() {
        return (Saddle)saddleInput.getSelectedItem();
    }
    
    public Horseshoes getHorseshoes() {
        return (Horseshoes)horseshoesInput.getSelectedItem();
    }
    
    public Accessory getAccessory() {
        return (Accessory)accessoryInput.getSelectedItem();
    }

    private double calculateConfidence(){
        double confidence=0.5;
        if(this.theHorse!=null){confidence=theHorse.getConfidence();}
        confidence *= this.getBreed().getConfidenceModifier() * this.getSaddle().getConfidenceModifier() * this.getHorseshoes().getConfidenceModifier() 
        * this.getAccessory().getConfidenceModifier();
        if (confidence>1.0){
            return 1.0;
        }
        else if (confidence<=0.0){
            return 0.1;
        }
        return confidence;
    }

    private double calculateSpeed(){
        double speed = 3.0 + this.getBreed().getSpeedModifier() + this.getSaddle().getSpeedModifier() + this.getHorseshoes().getSpeedModifier() 
        + this.getAccessory().getSpeedModifier();
        if(speed<=0.0){
            return 0.1;
        }
        return speed;
    }

    private int calculateEndurance(){
        int endurance = 20 + this.getBreed().getEnduranceModifier() + this.getSaddle().getEnduranceModifier() + this.getHorseshoes().getEnduranceModifier() 
        + this.getAccessory().getEnduranceModifier();
        System.out.println(endurance);
        if(endurance<=0){
            return 1;
        }
        return endurance;
    }

    private void updateLabels(){
        finalConfidenceLabel.setText(App.roundDouble(this.calculateConfidence(), 3)+"");
        finalSpeedLabel.setText(App.roundDouble(this.calculateSpeed(), 3)+"");
        finalEnduraceLabel.setText(this.calculateEndurance()+"");
    }    
}