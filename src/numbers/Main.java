package numbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, user!\nEnter filename to read");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String mode = Menu.modeSelection();
        while (!(mode.equals("1") || mode.equals("2"))) {
            try {
                mode = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        switch (mode) {
            case "1":
                File f = null;
                while (!(f = new File(Menu.readFileName())).exists() || f.isDirectory()) {
                    System.out.println("File is not existing. Enter filename to read from.");
                }
                Numbers.readMode(f.getPath());
                break;
            case "2":
                Numbers.writeMode();
                break;
            default:
                System.out.println("Wrong option");
                break;
        }
    }

}
