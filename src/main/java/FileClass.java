import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileClass {
    //DONE : GET DEFAULT MINECRAFT PATH AND ALLOW CHOOSING OF WORLD
    //DONE : allow multiple paths
    private static ArrayList<File> paths=new ArrayList<>();
    //DONE : MAkE PATH VARIABLE
    private static File path;// = new File("C:\\Users\\Elias Neel\\AppData\\Roaming\\.minecraft\\saves\\Deboys_)(World 1)\\advancements");
    private static List<JSONObject> items = new ArrayList<>();
    public static boolean pathCorrect = false;

    //DONE : choose world folder and get advancement folder in code
    //TODO : ADD ERROR CHECKING
    public static void openFileChooser() {
        String homeDir = System.getProperty("user.home");
        System.out.println(homeDir);
        File mcPath = new File(homeDir + "\\Appdata\\Roaming\\.minecraft\\saves");
        JFileChooser chooser = new JFileChooser(mcPath);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(true);
        int returnvalue = chooser.showSaveDialog(null);
        if (returnvalue == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile();
            if (path.isDirectory() && path.getPath().endsWith("advancements")) {
                paths.add(path);
                pathCorrect = true;
            }else{
                Pattern pattern= Pattern.compile("(\\.minecraft\\saves)?");
                Matcher matcher =pattern.matcher(path.toString());
                if(matcher.find()){
                    String newpath=path.toString()+"\\advancements";
                    System.out.println(newpath);
                    path=new File(newpath);
                    paths.add(path);
                    pathCorrect=true;
                }
            }
        } else {
            pathCorrect = false;
        }
    }

    //Will return of every JSON object from every path
    public static List<JSONObject> showAdvancements() {
        for (File f : paths
             ) {
            for (Path p : listFiles(f)) {
                JSONParser parser = new JSONParser();
                try {
                    JSONObject jsonfile = (JSONObject) parser.parse(new FileReader(p.toString()));
                    for (Object j : jsonfile.values()
                    ) {
                        items.add((JSONObject) j);
                    }
                } catch (Exception e) {
                    // TODO : IETS MET DE ERRORS ELIASJE
                }
            }
        }
        return items;
    }

    //Show all the files in the advancement folder
    private static List<Path> listFiles(File folder) {
        List<Path> files = new ArrayList<>();
        for (File fileEntry : folder.listFiles()
        ) {
            if (fileEntry.isDirectory()) {
                //TODO : iets met geneste folders?
                //listFiles(fileEntry);
            } else {
                //System.out.println(fileEntry.getName());
                files.add(Path.of(fileEntry.getPath()));
            }
        }
        return files;
    }

    public static String getCriteria(JSONObject j) {
        return j.get("criteria").toString();
    }

    //DONE : FILTER OPTIONS BASED ON COMPLETION
    public static List<JSONObject> showCompleted() {
        List<JSONObject> items = new ArrayList<>();
        for (JSONObject j : showAdvancements()
        ) {
            if (!j.get("done").toString().contains("true")) {
                items.add(j);
            }
        }
        return items;
    }

    //DONE : FILTER OPTIONS BASED ON DOING
    public static List<JSONObject> showDoing() {
        List<JSONObject> items = new ArrayList<>();
        for (JSONObject j : showAdvancements()
        ) {
            if (j.get("done").toString().contains("true")) {
                items.add(j);
            }
        }
        return items;
    }
}
