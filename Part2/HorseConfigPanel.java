import java.awt.*;
import javax.swing.*;

public class HorseConfigPanel{

    private JPanel horsePanel;
    private JTextField nameInput;
    private JComboBox breedInput;

    public HorseConfigPanel(){
        this.horsePanel=new JPanel();
        this.horsePanel.setLayout(new GridBagLayout());
        this.createLabels();
        this.createNameInput();
        this.createBreedInput();
    }

    private void createLabels(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        this.horsePanel.add(new JLabel("Name :"),gbc);
        gbc.gridy=1;
        this.horsePanel.add(new JLabel("Confidence :"),gbc);
        gbc.gridy=2;
        this.horsePanel.add(new JLabel("Speed :"),gbc); 
        gbc.gridy=3;
        this.horsePanel.add(new JLabel("Endurance :"),gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        this.horsePanel.add(new JLabel("Breed :"),gbc);
        gbc.gridy=1;
        this.horsePanel.add(new JLabel("Symbol :"),gbc);
        gbc.gridy=2;
        this.horsePanel.add(new JLabel("Colour :"),gbc);

        gbc.gridx=5;
        gbc.gridy=0;
        this.horsePanel.add(new JLabel("Saddle :"),gbc);
        gbc.gridy=1;
        this.horsePanel.add(new JLabel("Horseshoe :"),gbc);
        gbc.gridy=2;
        this.horsePanel.add(new JLabel("Accessory :"),gbc);
    }

    private void createNameInput(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        nameInput= new JTextField(10);
        horsePanel.add(nameInput,gbc);
    }

    private void createBreedInput(){
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridx=4;
        gbc.gridy=0;
        breedInput= new JComboBox(new Breed[] {Breed.THOROUGHBRED,Breed.ARABIAN,Breed.CLYDESDALE,Breed.MUSTANG,Breed.QUARTER_HORSE});
        breedInput.setSelectedIndex(0);
        horsePanel.add(breedInput,gbc);
    }

    public JPanel getHorsePanel(){
        return this.horsePanel;
    }
}