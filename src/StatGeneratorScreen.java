import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class StatGeneratorScreen extends JFrame {

  private String[] heroNames;
  private Random random = new Random();
  private String[] statNames = { "Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma" };
  private JLabel[] statLabels;
  private JLabel remainingPointsLabel;
  public StatGeneratorScreen(String[] heroNames){

    super("Stat Generator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);

    this.heroNames = heroNames;
    // Create a panel to hold the components
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4 + 3 * 2, 1));
    panel.add(new JLabel("Generating stats for the following heroes:"));
    panel.add(new JLabel("The six stats are: strength, dexterity, constitution"));
    panel.add(new JLabel("    intelligence, wisdom, charisma"));
    // Display hero names and fillable boxes for stats
    for (int i = 0; i < 3; i++) {
      JLabel nameLabel = new JLabel("Hero " + (i + 1) + ": " + heroNames[i]);
      panel.add(nameLabel);

      // Create a nested panel for the fillable boxes
      JPanel statsPanel = new JPanel();
      StatGenerator charStats = new StatGenerator();
      // Add fillable boxes for stats
      for (int j = 0; j < 6; j++) {
        JTextField textField = new JTextField();
        textField.setText(charStats.getStatBlock()[j]);
        textField.setPreferredSize(new Dimension(30, 20)); // Set preferred size
        statsPanel.add(textField);
      }

      panel.add(statsPanel);
    }

    add(panel);

  setLocationRelativeTo(null); // Center the window on the screen
}

}
