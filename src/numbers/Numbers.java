package numbers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    private static int K = 1;

    public static void setK(int k) {
        K = k;
    }
    public static int getK() {
        return K;
    }

    static void readMode(String file) {
        System.out.println("Entering read mode");
        List<String> strings = readFile(file);
        List<Float> numbers = null;
        Float f = 0f;
        List<Float> [] floatArrays = new List[strings.size()];
        for (int i = 0, stringsSize = strings.size(); i < stringsSize; i++) {
            String s = strings.get(i);
            List<String> nums = Arrays.asList(s.split(" "));
            nums = nums.stream().filter(n -> n.matches("[-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|0\\.[0-9]+|0")).collect(Collectors.toList());//nums is array of numbers in line
            if (nums.size() < 1 || nums.size() > K) {
                System.out.println("Amount of numbers in line " + i + " is violating K-size restriction.");
                floatArrays[i] = new ArrayList<>(0);
                continue;
            }
            numbers = new ArrayList<>(K);
            for (String n : nums) {
                f = Float.parseFloat(n);
                numbers.add(f);
                //System.out.println(f);
            }
            floatArrays[i] = numbers;
        }

        for (int j = 0; j < floatArrays.length; j++) {
            numbers = floatArrays[j];
            System.out.println(numbers.toString());
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
                        if (numbers.size() == 1) {
                            System.out.println("can't remove. Violation of arr.size >= 1");
                            i--;continue;
                        }
                        numbers.remove(i);
                        i--;
                        break;
                    case "4":
                        if (numbers.size() == K) {
                            System.out.println("can't add. violation of K restriction");
                            i--;continue;
                        }
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
        }

        //when read all file save it
        switch (Menu.saveOption()) {
            case "1":
                writeNumbersToFile(file, floatArrays);
                break;
            case "2":
                writeNumbersToFile(Menu.readFileName(), floatArrays);
                break;
            default:
                System.out.println("no such option");
                break;
        }
    }

    static void writeMode() {
        System.out.println("Entering write mode");
        int n = Menu.numbersToSave();//N lines to save
        List<Float>[] floatArrays = new List[n];
        List<Float> numbers;
        for (int i = 0; i < n; i++) {
            numbers = Menu.readUserFloats();
            if (numbers.size() > K || numbers.size() < 1) {
                System.out.println("bad array. violating K restriction");
                i--;
                continue;
            } else {
                floatArrays[i] = numbers;
            }
        }
        System.out.println("Saving numbers...");
        writeNumbersToFile(Menu.readFileName(), floatArrays);
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
                String s = reader.readLine().trim();
                if (!s.isEmpty()) {
                    strings.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return strings;
    }

    private static void writeNumbersToFile(String file, List<Float> numbers) {
        file = Menu.checkFileName(file);
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

    private static void writeNumbersToFile(String file, List<Float>[] numberArrays) {
        file = Menu.checkFileName(file);
        List<Float> numbers;
        Float f;
        try (FileWriter writer = new FileWriter(file, false)) {
            for (int j = 0; j < numberArrays.length; j++) {
                numbers = numberArrays[j];
                for (int i = 0; i < numbers.size(); i++) {
                    f = numbers.get(i);
                    writer.write(f.toString());
                    writer.append(' ');
                }
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}