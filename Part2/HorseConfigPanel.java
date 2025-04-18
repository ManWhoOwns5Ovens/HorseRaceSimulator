import java.awt.*;
import javax.swing.*;

public class HorseConfigPanel extends JPanel{
    private JTextField nameInput;
    private JComboBox symbolInput;
    private JComboBox colourInput;
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

    private void createNameInput(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        nameInput= new JTextField(10);
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
        this.add(new JLabel("0.5 -> "),gbc);

        gbc.gridy=2;
        this.add(new JLabel("3.0 -> "),gbc);

        gbc.gridy=3;
        this.add(new JLabel("20 -> "),gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        finalConfidenceLabel=new JLabel(this.calculateConfidence()+"");
        this.add(finalConfidenceLabel,gbc);

        gbc.gridy=2;
        finalSpeedLabel=new JLabel(this.calculateSpeed()+"");
        this.add(finalSpeedLabel,gbc);

        gbc.gridy=3;
        finalEnduraceLabel=new JLabel(this.calculateEndurance()+"");
        this.add(finalEnduraceLabel,gbc);
    }

    private void createComboBoxes(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridx=4;
        gbc.gridy=0;
        breedInput=createComboBox(Breed.ALL_BREEDS);
        this.add(breedInput,gbc);
        breedInput.addActionListener(e -> updateLabels());

        gbc.gridy=1;
        symbolInput=createComboBox(new String[]{"♘","♞","🐎","🏇","🦄"});
        this.add(symbolInput,gbc);

        gbc.gridy=2;
        colourInput=createComboBox(new String[]{"BLACK","GRAY","WHITE","DARK_GRAY"});
        this.add(colourInput,gbc);

        gbc.gridx=6;
        gbc.gridy=0;
        saddleInput=createComboBox(Saddle.ALL_SADDLES);
        this.add(saddleInput,gbc);
        saddleInput.addActionListener(e -> updateLabels());

        gbc.gridy=1;
        horseshoesInput=createComboBox(Horseshoes.ALL_HORSESHOES);
        this.add(horseshoesInput,gbc);
        horseshoesInput.addActionListener(e -> updateLabels());

        gbc.gridy=2;
        accessoryInput=createComboBox(Accessory.ALL_ACCESSORIES);
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
        return Color.getColor(colourInput.getSelectedItem().toString());
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
        return 0.5 * this.getBreed().getConfidenceModifier() * this.getSaddle().getConfidenceModifier() * this.getHorseshoes().getConfidenceModifier() 
        * this.getAccessory().getConfidenceModifier();
    }

    private double calculateSpeed(){
        return 3.0 + this.getBreed().getSpeedModifier() + this.getSaddle().getSpeedModifier() + this.getHorseshoes().getSpeedModifier() 
        + this.getAccessory().getSpeedModifier();
    }

    private int calculateEndurance(){
        return 20 + this.getBreed().getEnduranceModifier() + this.getSaddle().getEnduranceModifier() + this.getHorseshoes().getEnduranceModifier() 
        + this.getAccessory().getEnduranceModifier();
    }

    private void updateLabels(){
        finalConfidenceLabel.setText(App.roundDouble(this.calculateConfidence(), 3)+"");
        finalSpeedLabel.setText(App.roundDouble(this.calculateSpeed(), 3)+"");
        finalEnduraceLabel.setText(this.calculateEndurance()+"");
    }
    
}