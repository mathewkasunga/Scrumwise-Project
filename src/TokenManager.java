import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    // List of available tokens in the game.
    private final List<String> tokens = new ArrayList<>(List.of(
            "Top Hat", "Battleship", "Racecar", "Thimble",
            "Boot", "Dog", "Wheelbarrow", "Iron"
    ));

    public List<String> getTokens() {
        return new ArrayList<>(tokens); // Return a copy to avoid direct modification
    }

    // Assigns a token to a player and updates the UI accordingly
    public boolean assignToken(Player player, String token) {
        if (tokens.remove(token)) { // Removes token from available list if it exists
            player.setToken(token); // Assigns the token to the player
            notifyTokenAssigned(player); // Notify UI
            return true;
        }
        return false; // Token was not available
    }

    // Notify the game UI to update the player panel
    private void notifyTokenAssigned(Player player) {
        GameUI.updateProfiles(); // Refresh side panel with updated player token
    }
}
