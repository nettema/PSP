package numbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Menu {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String modeSelection() {
        System.out.println("Chose mode!\n1. is for read mode\n2. is for write mode\n3. regression count for k=2");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[123]")) {
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
                System.out.println("Enter the number of a valid form.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Float readUserFloat() {
        return Float.parseFloat(readUserDigit());
    }

    public static String readUserDigits() {
        System.out.println("Please, enter a list of numbers separated by space");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("([-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|[-]?0\\.[0-9]+|0)(\\h([-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|[-]?0\\.[0-9]+|0))*")) {
                System.out.println("Enter the number of a valid form.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Float> readUserFloats() {
        String[] nums = readUserDigits().split("\\h");
        List<Float> floats = new ArrayList<>(nums.length);
        for (String num : nums) {
            floats.add(Float.parseFloat(num));
        }
        return floats;
    }
    public static List<Integer> readUserInts() {
        String[] nums = readUserDigits().split("\\h");
        List<Integer> ints = new ArrayList<>(nums.length);
        for (String num : nums) {
            ints.add(Integer.parseInt(num));
        }
        return ints;
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

    public static String yesNoChoice(String msg) {
        System.out.println(msg);
        System.out.println("1. Yes");
        System.out.println("2. No");
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
            while (!(file = reader.readLine().trim()).matches("([a-zA-Z][:])?[-_.\\\\A-Za-z0-9]+")) {
                System.out.println("Enter valid file name");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String checkFileName(String fName) {
        File file = new File(fName);
        if (file.exists() && file.isFile()) {
            switch (Menu.yesNoChoice("File is already exists, do you want to rewrite it?")) {
                case "1":
                    return fName;
                case "2":
                    return checkFileName(readFileName());
            }
        }
        return fName;
    }

    public static int numbersToSave() {
        System.out.println("Enter ammount of lines you wish to save?");
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

    public static int readArrayLimit() {
        System.out.println("Please, array's size limit");
        String res = "";
        try {
            while (!(res = reader.readLine().trim()).matches("[1-9]{1}[0-9]*")) {
                System.out.println("Enter the integer number of a valid form.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(res);
    }
}
