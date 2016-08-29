package numbers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    static void readMode(String file) {
        System.out.println("Entering read mode");
        List<String> strings = readFile(file);
        List<Float> numbers = new LinkedList<>();
        Float f = 0f;
        for (String s : strings) {
            List<String> nums = Arrays.asList(s.split(" "));
            nums = nums.stream().filter(n -> n.matches("[-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|0\\.[0-9]+|0")).collect(Collectors.toList());
            for (String n : nums) {
                f = Float.parseFloat(n);
                numbers.add(f);
                //System.out.println(f);
            }
        }

        for (int i = 0; i < numbers.size(); i++) {
            f = numbers.get(i);
            System.out.println(f);
            switch (Menu.numAction()) {
                case "1":
                    break;
                case "2":
                    f = Float.parseFloat(Menu.readUserDigit());
                    numbers.set(i, f);
                    break;
                case "3":
                    numbers.remove(i);
                    i--;
                    break;
                case "4":
                    f = Menu.readUserFloat();
                    switch (Menu.insertPos()) {
                        case "1":
                            numbers.add(++i, f);
                            break;
                        case "2":
                            numbers.add(i++, f);
                            break;
                    }
                    break;
                default:
                    System.out.println("not valid option");
                    i--;
                    break;
            }
        }
        //when read all file save it
        switch (Menu.saveOption()) {
            case "1":
                writeNumbersToFile(file, numbers);
                break;
            case "2":
                writeNumbersToFile(Menu.readFileName(), numbers);
                break;
            default:
                System.out.println("no such option");
                break;
        }
    }

    static void writeMode() {
        System.out.println("Entering write mode");
        int n = Menu.numbersToSave();
        List<Float> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(Menu.readUserFloat());
        }
        System.out.println("Saving numbers...");
        writeNumbersToFile(Menu.readFileName(), numbers);
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

    private static void writeNumbersToFile(String file, List<Float> numbers) {
        Float f;
        try (FileWriter writer = new FileWriter(file, false)) {
            for (int i = 0; i < numbers.size(); i++) {
                f = numbers.get(i);
                writer.write(f.toString());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}