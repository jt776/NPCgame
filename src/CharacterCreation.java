
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CharacterCreation extends JFrame {

  private static final DLList<String> randomNames = new DLList();

  private JTextField[] nameFields;
  private static void addNames(){
    randomNames.add("Jon");
    randomNames.add("Jana");
    randomNames.add("Dragomir");
    randomNames.add("Wes");
    randomNames.add("Arron");
    randomNames.add("Lena");
    randomNames.add("Flyn");
    randomNames.add("Grannit");
    randomNames.add("Yanis");
    randomNames.add("Eva");
    randomNames.add("Idris");
    randomNames.add("Gwen");
    randomNames.add("Devon");
    randomNames.add("Esemir");
    randomNames.add("Axel");
    randomNames.add("Mira");
    randomNames.add("Darian");
    randomNames.add("Flo");
    randomNames.add("Issi");
    randomNames.add("Dafne");

  }

    public CharacterCreation() {
      super("Character Creation");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 300);

      // Create a label and text field to enter the hero name
      JLabel nameLabel1 = new JLabel("Let's name your first hero:");
      JLabel nameLabel2 = new JLabel("Let's name your second hero:");
      JLabel nameLabel3 = new JLabel("Let's name your third hero:");
      nameFields = new JTextField[3];
      for (int i = 0; i < 3; i++) {
        nameFields[i] = new JTextField(20);
      }
      JButton randomButton = new JButton("Random");
      JButton createButton = new JButton("Create");

      // Add an ActionListener to the random button
      randomButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // Generate a random name from the predefinedNames array
          Random random = new Random();
          addNames();
          for (int i = 0; i < 3; i++) {
            int index = random.nextInt(randomNames.size());
            String randomName = randomNames.get(index);
            nameFields[i].setText(randomName);
            randomNames.remove(i);
          }
        }
      });

      // Create a "Create" button to proceed
      createButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // Handle button click event
          String[] heroNames = new String[3];
          for (int i = 0; i < 3; i++) {
            heroNames[i] = nameFields[i].getText();
          }
          StatGeneratorScreen statGeneratorScreen = new StatGeneratorScreen(heroNames);
          statGeneratorScreen.setVisible(true);
          dispose(); // Close the character creation screen
        }
      });

      // Create a panel to hold the components
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(4, 2));
      panel.add(nameLabel1);
      panel.add(nameFields[0]);
      panel.add(nameLabel2);
      panel.add(nameFields[1]);
      panel.add(nameLabel3);
      panel.add(nameFields[2]);
      panel.add(randomButton);
      panel.add(createButton);

      add(panel);

      setLocationRelativeTo(null); // Center the window on the screen
    }
  }
