import java.awt.*;
import javax.swing.*;

public class Field extends JPanel {
    public static final int GAME_MODE_HVH = 0;
    public static final int GAME_MODE_HVA = 1;

    Field() {
        setBackground(Color.BLACK);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("game mode: %d\nfieldSize: %d\nwinLength: %d",
                gameMode, fieldSizeX, winLength);
    }
}
