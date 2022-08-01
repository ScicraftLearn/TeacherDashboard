import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame mainFrame = new JFrame();
    private static JPanel advPanel = new JPanel();

    public static void main(String[] args) {
        getButtons();
        mainFrame.setSize(800, 600);
        mainFrame.add(advPanel);
        mainFrame.setVisible(true);
    }

    private static void getButtons() {
        for (JSONObject j : FileClass.showAdvancements()
        ) {
            advPanel.add(new JButton(FileClass.getCriteria(j)));
        }
    }
}