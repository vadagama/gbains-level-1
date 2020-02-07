import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private final static int WINDOW_WIDTH = 350;
    private final static int WINDOW_HEIGHT = 200;
    private MainWindow mainWindow;

    private JRadioButton humanVSComputerRadio;
    private JRadioButton humanVSHumanRadio;
    private JSlider filedSizeSlider;
    private JSlider winLengthSlider;
    private JLabel gameModeLabel;
    private JLabel sizeLabel;
    private JLabel winSizeLabel;
    private ButtonGroup gameModeButtonGroup;
    private JButton buttonStart;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int MIN_WIN_SIZE = 3;


    Settings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle gameWindowBounds = mainWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("Create new game");
        setLayout(new GridLayout(10, 1));
        createGameModeControls();
        buttonStart = new JButton("Start Game");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(buttonStart);

    }
    private void createGameModeControls() {
        // Radio buttons
        gameModeLabel = new JLabel("Choose your mode");
        add(gameModeLabel);
        humanVSComputerRadio = new JRadioButton("Human vs Computer");
        humanVSHumanRadio = new JRadioButton("Human vs Human");
        gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(humanVSComputerRadio);
        gameModeButtonGroup.add(humanVSHumanRadio);
        add(humanVSComputerRadio);
        add(humanVSHumanRadio);

        //Sliders
        sizeLabel = new JLabel("Choose field size");
        winSizeLabel = new JLabel("Choose field size for winning");
        filedSizeSlider = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        winLengthSlider = new JSlider(MIN_WIN_SIZE, MIN_FIELD_SIZE, MIN_WIN_SIZE);
        filedSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
              int actualSizeValue = filedSizeSlider.getValue();
              sizeLabel.setText("" + actualSizeValue);
              winLengthSlider.setMaximum(actualSizeValue);
            }
        });
        winLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int actualWinValue = winLengthSlider.getValue();
                winSizeLabel.setText("" + actualWinValue);
            }
        });
        add(sizeLabel);
        add(filedSizeSlider);
        add(winSizeLabel);
        add(winLengthSlider);
    }

    private void startGame() {
        int gameMode = Field.GAME_MODE_HVH;
        if (humanVSComputerRadio.isSelected()) {
            gameMode = Field.GAME_MODE_HVA;
        }
        else if (humanVSHumanRadio.isSelected()) {
            gameMode = Field.GAME_MODE_HVH;
        }

        int fieldSize = filedSizeSlider.getValue();
        int winLength = winLengthSlider.getValue();

        mainWindow.start(gameMode, fieldSize, fieldSize, winLength);
        setVisible(false);
    }
}
