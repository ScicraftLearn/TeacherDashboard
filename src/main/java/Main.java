import org.json.simple.JSONObject;

import javax.swing.*;

public class Main {

    //TODO : fix layout

    private static JFrame mainFrame = new JFrame();
    private static JPanel advPanel = new JPanel();

    public static void main(String[] args) {
        HotKeyClass.startListeners();
        getButtons();
        mainFrame.setSize(800, 600);
        mainFrame.add(advPanel);
        mainFrame.setVisible(true);
    }

    private static void getButtons() {
        for (JSONObject j : FileClass.showAdvancements()
        ) {
            System.out.println(j.toString());
            advPanel.add(new JButton(FileClass.getCriteria(j)));
        }
    }
}