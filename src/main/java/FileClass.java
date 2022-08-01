import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileClass {
    private static File path = new File("C:\\Users\\Elias Neel\\AppData\\Roaming\\.minecraft\\saves\\Deboys_)(World 1)\\advancements");
    private static List<JSONObject> items = new ArrayList<>();

    public static List<JSONObject> showAdvancements() {
        //listFiles(path);
        for (Path p : listFiles(path)) {
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
