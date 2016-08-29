import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    public static void main(String[] args) {
        System.out.println("Hello, user!\nEnter filename to read");
//        String file = getFileName();
        String file;
        System.out.println("Chose mode!\n 1. is for read mode\n2. is for write mode");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String mode = "";
        while (!(mode.equals("1") || mode.equals("2"))) {
            try {
                mode = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file = "test.txt";
        switch (mode) {
            case "1":
                readMode(file);
                break;
            case "2":
                writeMode(file);
        }
    }

    private static void readMode(String file) {
        System.out.println("Entering read mode");
        List<String> strings = readFile(file);
        for (String s : strings) {
            List<String> nums = Arrays.asList(s.split(" "));
            nums = nums.stream().filter(n -> n.matches("[-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|0\\.[0-9]+|0")).collect(Collectors.toList());
            for (String n : nums) {
                System.out.println(n);
            }
        }
    }

    private static void writeMode(String file) {
        System.out.println("Entering write mode");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter ammout of numbers to be saved");
        int n = 0;
        while (n <= 0) {
            try {
                n = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(file, false)) {
            for (int i = 0; i < n; i++) {
                String s;
                while (!(s = reader.readLine()).matches("[-]?[1-9]{1}[0-9]*[[\\.]{1}[0-9]*]?|0\\.[0-9]+|0")) {
                    System.out.println("enter valid number");
                }
                Float f = Float.parseFloat(s);
                writer.write(f.toString());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
        System.out.println(ex.getMessage());
        }
    }

    private static String getFileName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = null;
        while (file == null || file.isEmpty()) {
            try {
                file = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private static List<String> readFile(String file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> strings = new LinkedList<>();
        try {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fileReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return strings;
    }
}
