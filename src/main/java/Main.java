import org.json.simple.JSONObject;

import javax.swing.*;

public class Main {

    //TODO : fix layout

    private static JFrame mainFrame = new JFrame();
    private static JPanel advPanel = new JPanel();
    //TODO : implement button for filtering
    private static Boolean filtered=null;

    public static void main(String[] args) {
        HotKeyClass.startListeners();
        getButtons();
        mainFrame.setSize(800, 600);
        mainFrame.add(advPanel);
        mainFrame.setVisible(true);
    }

    //DONE : filter buttons
    private static void getButtons() {
        if(filtered != null && !filtered){
            for (JSONObject j:FileClass.showDoing()
            ) {
                System.out.println(j.toString());
                advPanel.add(new JButton(FileClass.getCriteria(j)));
            }
        } else if(filtered!=null && filtered){
            for (JSONObject j:FileClass.showCompleted()
                 ) {
                System.out.println(j.toString());
                advPanel.add(new JButton(FileClass.getCriteria(j)));
            }
        }else{
            for (JSONObject j : FileClass.showAdvancements()
            ) {
                System.out.println(j.toString());
                advPanel.add(new JButton(FileClass.getCriteria(j)));
            }
        }
    }
}