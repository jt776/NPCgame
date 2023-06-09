import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

  public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
      super("Welcome Screen");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 300);

      // Create a label with a greeting message
      JLabel label = new JLabel("Last Minute Legends");
      label.setFont(new Font("Arial", Font.BOLD, 24));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      add(label, BorderLayout.CENTER);

      // Create a button to proceed
      JButton button = new JButton("Play");
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // Handle button click event
          CharacterCreation characterCreationScreen = new CharacterCreation();
          characterCreationScreen.setVisible(true);
          dispose(); // Close the welcome screen
        }
      });
      add(button, BorderLayout.SOUTH);

      setLocationRelativeTo(null); // Center the window on the screen
    }

    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          WelcomeScreen welcomeScreen = new WelcomeScreen();
          welcomeScreen.setVisible(true);
        }
      });
    }
  }

