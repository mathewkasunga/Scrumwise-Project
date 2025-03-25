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
                name = JOptionPane.showInputDialog("Player "
