import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayToken extends JPanel {
    private final int CELL_SIZE = 55;
    private final int CORNER_SIZE = CELL_SIZE * 2;
    private final int BOARD_SIZE = CELL_SIZE * 9 + CORNER_SIZE * 2;
    private final List<Player> players;
    private final List<Property> properties;

    public DisplayToken(List<Player> players, List<Property> properties) {
        this.players = players;
        this.properties = properties;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPlayers(g);
        drawProperties(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, BOARD_SIZE, BOARD_SIZE);

        for (int i = 0; i < 9; i++) {
            g.drawRect(50 + CORNER_SIZE + i * CELL_SIZE, 50 + BOARD_SIZE - CORNER_SIZE, CELL_SIZE, CORNER_SIZE);
            g.drawRect(50 + CORNER_SIZE + i * CELL_SIZE, 50, CELL_SIZE, CORNER_SIZE);
            g.drawRect(50, 50 + CORNER_SIZE + i * CELL_SIZE, CORNER_SIZE, CELL_SIZE);
            g.drawRect(50 + BOARD_SIZE - CORNER_SIZE, 50 + CORNER_SIZE + i * CELL_SIZE, CORNER_SIZE, CELL_SIZE);
        }
    }

    private void drawPlayers(Graphics g) {
        List<int[]> playerPositions = new ArrayList<>();
        for (Player player : players) {
            int[] cords = getBoardPosition(player.getPosition());

            int offsetX = 0;
            int offsetY = 0;
            for (int j = 0; j < playerPositions.size(); j++) {
                if (playerPositions.get(j)[0] == cords[0] && playerPositions.get(j)[1] == cords[1]) {
                    offsetX = (j + 1) * 12;
                    offsetY = (j + 1) * 12;
                }
            }

            playerPositions.add(new int[]{cords[0] + offsetX, cords[1] + offsetY});
            g.setColor(player.getColor());
            g.fillOval(cords[0] + offsetX + 5, cords[1] + offsetY + 5, 15, 15);
        }
    }

    private void drawProperties(Graphics g) {
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            int[] cords = getBoardPosition(i);

            if (property.getOwner() != null) {
                g.setColor(Color.RED); // Owned property in red
            } else {
                g.setColor(Color.BLUE); // Available property in blue
            }

            g.drawString(property.getName(), cords[0] + 5, cords[1] + 20);
        }
    }

    private int[] getBoardPosition(int position) {
        int x = 0, y = 0;
        position = position % 40;

        if (position < 10) {
            x = 50 + CORNER_SIZE + (9 - position) * CELL_SIZE;
            y = 50 + BOARD_SIZE - CORNER_SIZE;
        } else if (position < 20) {
            x = 50 + BOARD_SIZE - CORNER_SIZE;
            y = 50 + CORNER_SIZE + (position - 10) * CELL_SIZE;
        } else if (position < 30) {
            x = 50 + CORNER_SIZE + (position - 20) * CELL_SIZE;
            y = 50;
        } else if (position < 40) {
            x = 50;
            y = 50 + CORNER_SIZE + (29 - position) * CELL_SIZE;
        }

        return new int[]{x, y};
    }
}
