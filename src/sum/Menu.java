package sum;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nettema on 05.09.2016.
 */
public class Menu {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readFileName() {
        System.out.println("Please enter file name");
        String file = "";
        try {
            while (!(file = reader.readLine().trim()).matches("([a-zA-Z][:])?[-_.\\\\A-Za-z0-9]+")) {
                System.out.println("Enter valid file name");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getFile() {
        File file;
        while (!(file = new File(Menu.readFileName())).exists()) {
            System.out.println("File is not existing. Enter filename to read from.");
        }
        return file;
    }
}
