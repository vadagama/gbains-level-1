import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private static final int WIDTH_WINDOW = 500;
    private static final int HEIGHT_WINDOW = 500;
    private static final int X_WINDOW = 200;
    private static final int Y_WINDOW = 300;

    private Settings settings;
    private Field field;

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic tak toe game");
        setLocation(X_WINDOW, Y_WINDOW);
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);


        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");

        JPanel panelButtons = new JPanel(new GridLayout(1,2));
        panelButtons.add(startButton);
        panelButtons.add(exitButton);

        setLayout(new BorderLayout());
        add(panelButtons,BorderLayout.SOUTH);

        //Events on Button clicks
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        field = new Field();
        settings = new Settings(this);

        setVisible(true);

    }
}
