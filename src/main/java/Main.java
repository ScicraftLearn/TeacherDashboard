import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

    //TODO : create proper layout and buttons
    //TODO : fix layout
    //TODO : enable scrolling

    private static JFrame mainFrame = new JFrame();
    private static JPanel advPanel = new JPanel();
    private static Label text = new Label("Wrong directory");

    //Allowing for multiple files at once
    private static ArrayList<FileClass> files=new ArrayList<>();

    //TODO : implement button for filtering
    private static Boolean filtered=null;

    public static void main(String[] args) {
        loadFile();
        loadMenuBar();
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

    //TODO : IMPLEMENT BUTTON CLICK FUNCTIONALITY
    private static void loadMenuBar(){
        //File menu
        Menu fileMenu=new Menu("File");
        MenuItem openItem=new MenuItem("Open File");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                loadFile();
            }
        });
        MenuItem closeItem=new MenuItem("Close File");
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                mainFrame.setVisible(true);
            }
        });
        fileMenu.add(openItem);
        fileMenu.add(closeItem);

        //Filter menu
        Menu filterMenu=new Menu("Filter");
        MenuItem enableTrue=new MenuItem("Completed");
        enableTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO : IMPLEMENT LISTENER
            }
        });
        MenuItem enableFalse=new MenuItem("Uncompleted");
        enableFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO : IMPLEMENT LISTENER
            }
        });
        MenuItem disableFilter=new MenuItem("Show All");
        disableFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO : IMPLEMENT ACTION LISTENER
            }
        });
        filterMenu.add(enableTrue);
        filterMenu.add(enableFalse);
        filterMenu.add(disableFilter);

        //Create menubar
        MenuBar bar=new MenuBar();
        //Add all components
        bar.add(fileMenu);
        bar.add(filterMenu);
        mainFrame.setMenuBar(bar);
    }

    //DONE : FILE OPENING
    private static void loadFile(){
        FileClass.openFileChooser();
        if(FileClass.pathCorrect){
            HotKeyClass.startListeners();
            getButtons();
            mainFrame.setSize(800, 600);
            mainFrame.add(advPanel);
        }
        else {
            mainFrame.setSize(800,600);
            mainFrame.add(text);
        }
        mainFrame.setVisible(true);
    }
    private static void resetButtons(){
        mainFrame.setVisible(false);
        mainFrame.remove(text);
        mainFrame.remove(advPanel);
        advPanel=new JPanel();
    }
}