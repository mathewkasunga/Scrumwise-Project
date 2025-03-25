import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameInitializer {
    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManager();
        List<Player> players = new ArrayList<>();
        int numPlayers = 0;

        // Loop until a valid number of players (2-8) is entered
        while (true) {
            String input = JOptionPane.showInputDialog("Enter number of players (2-8):");

            // Handle cancel or closing the dialog
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Game setup canceled.");
                System.exit(0); // Exit the program safely
            }

            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers >= 2 && numPlayers <= 8) break; // Valid player count
                JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 8.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }

        List<String> playerNames = new ArrayList<>(); // To check for duplicate names

        // Loop to get player names and assign unique colors
        for (int i = 1; i <= numPlayers; i++) {
            String name;
            while (true) {
                name = JOptionPane.showInputDialog("Player " + i + " name:");

                // Handle case where user cancels the input
                if (name == null) {
                    JOptionPane.showMessageDialog(null, "Game setup canceled.");
                    System.exit(0);
                }

                name = name.trim(); // Remove leading/trailing whitespace

                // Validate player name input
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Player name cannot be empty.");
                } else if (playerNames.contains(name)) {
                    JOptionPane.showMessageDialog(null, "Player name already exists. Please enter a unique name.");
                } else {
                    playerNames.add(name);
                    break; // Valid name entered
                }
            }

            // Assign a unique color to each player (one of the predefined colors)
            Color color = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA}[i % 8];
            players.add(new Player(name, color)); // Create and add the player to the list
        }

        // After all players are set, show TokenSelectionUI before launching the game
        SwingUtilities.invokeLater(() -> {
            // Create Token Selection UI to assign tokens
            TokenSelectionUI tokenSelectionUI = new TokenSelectionUI(players, tokenManager, () -> {
                // After token selection, initialize the properties and launch the game UI
                List<Property> properties = new GameInitializer().initializeProperties();
                new GameUI(players, properties); // Launch the Game UI with players and properties
            });
            tokenSelectionUI.setVisible(true); // Make the token selection UI visible
        });
    }

    // Method to initialize the properties of the board
    private List<Property> initializeProperties() {
        List<Property> properties = new ArrayList<>();

        // Brown (Purple)
        properties.add(new Property("Mediterranean Avenue", 60));
        properties.add(new Property("Baltic Avenue", 60));

        // Light Blue
        properties.add(new Property("Oriental Avenue", 100));
        properties.add(new Property("Vermont Avenue", 100));
        properties.add(new Property("Connecticut Avenue", 120));

        // Pink
        properties.add(new Property("St. Charles Place", 140));
        properties.add(new Property("States Avenue", 140));
        properties.add(new Property("Virginia Avenue", 160));

        // Orange
        properties.add(new Property("St. James Place", 180));
        properties.add(new Property("Tennessee Avenue", 180));
        properties.add(new Property("New York Avenue", 200));

        // Red
        properties.add(new Property("Kentucky Avenue", 220));
        properties.add(new Property("Indiana Avenue", 220));
        properties.add(new Property("Illinois Avenue", 240));

        // Yellow
        properties.add(new Property("Atlantic Avenue", 260));
        properties.add(new Property("Ventnor Avenue", 260));
        properties.add(new Property("Marvin Gardens", 280));

        // Green
        properties.add(new Property("Pacific Avenue", 300));
        properties.add(new Property("North Carolina Avenue", 300));
        properties.add(new Property("Pennsylvania Avenue", 320));

        // Dark Blue
        properties.add(new Property("Park Place", 350));
        properties.add(new Property("Boardwalk", 400));

        // Railroads
        properties.add(new Property("Reading Railroad", 200));
        properties.add(new Property("Pennsylvania Railroad", 200));
        properties.add(new Property("B&O Railroad", 200));
        properties.add(new Property("Short Line Railroad", 200));

        // Utilities
        properties.add(new Property("Electric Company", 150));
        properties.add(new Property("Water Works", 150));

        return properties;
    }
}

