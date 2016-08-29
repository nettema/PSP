package numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String modeSelection() {
        System.out.println("Chose mode!\n1. is for read mode\n2. is for write mode");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[12]")) {
                System.out.println("Please, enter valid option");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String numAction() {
        System.out.println("What would you like to do with this number?");
        System.out.println("1. Accept");
        System.out.println("2. Replace");
        System.out.println("3. Delete");
        System.out.println("4. Insert new Number");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[1234]")) {
                System.out.println("Please, enter valid option");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static String insertPos() {
        System.out.println("What would you like to do with this number?");
        System.out.println("1. After");
        System.out.println("2. Before");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[12]")) {
                System.out.println("Please, enter valid option");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String readUserDigit() {
        System.out.println("Please, enter a number");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|0\\.[0-9]+|0")) {
                System.out.println("Enter valid number form.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Float readUserFloat() {
        return Float.parseFloat(readUserDigit());
    }

    public static String saveOption() {
        System.out.println("What would you like to save to existing file?");
        System.out.println("1. Yes");
        System.out.println("2. No. Save with different name");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[12]")) {
                System.out.println("Please, enter valid option");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String readFileName() {
        System.out.println("Please enter file name");
        String file = "";
        try {
            while (!(file = reader.readLine().trim()).matches("[-_.A-Za-z0-9]+")) {
                System.out.println("Enter valid file name");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static int numbersToSave() {
        System.out.println("Enter ammount of numbers you wish to save?");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[1-9]{1}[0-9]{0,7}|[0]")) {
                System.out.println("Please, enter valid option");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(res);
    }
}
