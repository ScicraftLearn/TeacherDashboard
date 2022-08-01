import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileClass {
    private static File path = new File("C:\\Users\\Elias Neel\\AppData\\Roaming\\.minecraft\\saves\\Deboys_)(World 1)\\advancements");

    public static void readfile() {
        //listFiles(path);
        for (Path p:listFiles(path)
             ) {
            File f=new File(path.toString());
        }
    }

    private static List<Path> listFiles(File folder) {
        List<Path> files=new ArrayList<>();
        for (File fileEntry: folder.listFiles()
             ) {
            if(fileEntry.isDirectory()){
                //TODO : iets met geneste folders?
                //listFiles(fileEntry);
            }else{
                System.out.println(fileEntry.getName());
                files.add(Path.of(fileEntry.getPath()));
            }
        }
        return files;
    }
}
