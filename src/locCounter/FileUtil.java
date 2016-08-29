package locCounter;

import java.io.File;

/**
 * Created by nettema on 24.08.2016.
 */
public class FileUtil {
    public static File getFile(String fileName) {
        File file = null;

        try {
            file = new File(fileName);
            if (!file.exists()) {
                System.out.println("No file with such name");
            }
            if (file.isDirectory()) {
                System.out.println(file.getName() + " is a directory");
                file = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
