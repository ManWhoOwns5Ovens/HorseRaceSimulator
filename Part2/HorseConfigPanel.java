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

    public HorseConfigPanel(){
        this.setLayout(new GridBagLayout());
        this.createLabels();
        this.createComboBoxes();
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

    private void createNameInput(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        nameInput= new JTextField(10);
        this.add(nameInput,gbc);
    }

    private void createComboBoxes(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=4;
        gbc.gridy=0;
        breedInput=createComboBox(Breed.ALL_BREEDS);
        this.add(breedInput,gbc);

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

        gbc.gridy=1;
        horseshoesInput=createComboBox(Horseshoes.ALL_HORSESHOES);
        this.add(horseshoesInput,gbc);

        gbc.gridy=2;
        accessoryInput=createComboBox(Accessory.ALL_ACCESSORIES);
        this.add(accessoryInput,gbc);
    }

    private JComboBox createComboBox(Object[] choices){
        JComboBox newBox= new JComboBox(choices);
        newBox.setSelectedIndex(0);
        return newBox;
    }
}