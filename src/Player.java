import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Color color;
    private int money = 1500;  // Initial money for each player
    private int position = 0;  // Starting position on the board
    private String token;
    private final List<Property> ownedProperties;  // List to track properties owned by the player

    // Constructor to initialize the player
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.ownedProperties = new ArrayList<>();
    }

    // Getters and Setters for Player attributes
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getMoney() {
        return money;
    }

    public String getToken() {
        return token;
    }

    public Color getColor() {
        return color;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Move the player across the board (position wraps around after 40 spaces)
    public void move(int steps) {
        position = (position + steps) % 40;
    }

    // Add a property to the player's list of owned properties
    public void buyProperty(Property property) {
        if (money >= property.getPrice()) {
            money -= property.getPrice();
            ownedProperties.add(property);
            property.setOwner(this);  // Set the player as the owner of the property
        } else {
            System.out.println(name + " does not have enough
