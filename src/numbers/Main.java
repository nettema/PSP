package numbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, user!\nEnter filename to read");
        String mode = Menu.modeSelection();
        System.out.println("Please enter K number which is maximal array size to be held");
        Numbers.setK(Menu.readArrayLimit());
        switch (mode) {
            case "1"://read mode
                File f = null;
                while (!(f = new File(Menu.readFileName())).exists() || f.isDirectory()) {
                    System.out.println("File is not existing. Enter filename to read from.");
                }
                Numbers.readMode(f.getPath());
                break;
            case "2"://write mode
                Numbers.writeMode();
                break;
            case "3"://regression
                f = null;
                while (!(f = new File(Menu.readFileName())).exists() || f.isDirectory()) {
                    System.out.println("File is not existing. Enter filename to read from.");
                }
                Numbers.setK(2);
                Numbers.regression(f.getPath());
                break;
            default:
                System.out.println("Wrong option");
                break;
        }
    }

}
