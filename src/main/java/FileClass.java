import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileClass {
    private static File path = new File("C:\\Users\\Elias Neel\\AppData\\Roaming\\.minecraft\\saves\\Deboys_)(World 1)\\advancements");

    public static void readfile() {
        //listFiles(path);
        for (Path p : listFiles(path)) {
            JSONParser parser=new JSONParser();
            try{
                JSONObject jsonfile =(JSONObject) parser.parse(new FileReader(p.toString()));
                //JSONArray advancements = (JSONArray) jsonfile.values();
                Iterator<JSONObject> it= jsonfile.entrySet().iterator();
                while (it.hasNext()){
                    System.out.println(it.next());
                }
            }catch (Exception e){
                // TODO : IETS MET DE ERRORS ELIASJE
            }
        }
    }

    private static List<Path> listFiles(File folder) {
        List<Path> files = new ArrayList<>();
        for (File fileEntry : folder.listFiles()
        ) {
            if (fileEntry.isDirectory()) {
                //TODO : iets met geneste folders?
                //listFiles(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                files.add(Path.of(fileEntry.getPath()));
            }
        }
        return files;
    }
}
