import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    // List of available tokens in the game
    private final List<String> tokens = new ArrayList<>(List.of(
            "Top Hat", "Battleship", "Racecar", "Thimble",
            "Boot", "Dog", "Wheelbarrow", "Iron"
    ));

    // List to track which tokens are assigned to players
    private final List<String> assignedTokens = new ArrayList<>();

    // Get a copy of the available tokens
    public List<String> getTokens() {
        // Return tokens that are available (not assigned to players)
        List<String> availableTokens = new ArrayList<>(tokens);
        availableTokens.removeAll(assignedTokens);  // Remove assigned tokens from the list
        return availableTokens;
    }

    // Assign a token to a player if available
    public boolean assignToken(Player player, String token) {
        // Check if the token is available
        if (tokens.contains(token) && !assignedTokens.contains(token)) {
            assignedTokens.add(token);  // Mark the token as assigned
            player.setToken(token);  // Assign the token to the player
            return true;  // Token successfully assigned
        }
        return false;  // Token was not available or already assigned
    }

    // Return a token when a player is eliminated (e.g., bankrupt)
    public void returnToken(Player player) {
        String token = player.getToken();
        if (token != null && assignedTokens.contains(token)) {
            assignedTokens.remove(token);  // Remove token from assigned tokens list
            tokens.add(token);  // Add token back to available tokens list
            player.setToken(null);  // Remove token from the player
        }
    }
}
