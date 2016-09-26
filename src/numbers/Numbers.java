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

    static void readMode(String file) {
        System.out.println("Entering read mode");
        List<String> strings = readFile(file);
        List<Float> numbers = null;
        Float f = 0f;
        List<Float>[] floatArrays = getFloatArray(file);

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
                            i--;
                            continue;
                        }
                        numbers.remove(i);
                        i--;
                        break;
                    case "4":
                        if (numbers.size() == K) {
                            System.out.println("can't add. violation of K restriction");
                            i--;
                            continue;
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

    public static void sort(String file) {
        System.out.println("Entering read mode");
        List<Float>[] floatArrays = getFloatArray(file);
        Pair[] pairs = new Pair[floatArrays.length];
        System.out.println("enter row number to sort");
        int pos = Menu.readArrayLimit() - 1;
        if (pos < 0 || pos >= floatArrays[0].size()) {
            System.out.println("Key column num is out of range!");
            return;
        }
        for (int i = 0, floatArraysLength = floatArrays.length; i < floatArraysLength; i++) {
            List<Float> row = floatArrays[i];
            pairs[i] = new Pair(row.get(pos), row);
        }
        pairs = (Pair[]) mergeSort(pairs);
        for (Pair p : pairs) {
            System.out.println(p.getValue());
        }
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
        } finally {
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

    public static void regression(String file) {
        System.out.println("Regression calc mode");
        List<Float>[] floatArrays = getFloatArray(file);
        List<Integer> planedX = Menu.readUserInts();
        float N = floatArrays.length;
        float sumX = 0;
        float sumY = 0;
        float sumXY = 0;
        float sumXSqr = 0;
        float avgX, avgY;
        for (int i = 0, floatArraysLength = floatArrays.length; i < floatArraysLength; i++) {
            List<Float> floatArray = floatArrays[i];
            if (floatArray.size() != 2) {
                N--;
                continue;
            }
            float x = floatArray.get(0);
            float y = floatArray.get(1);
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXSqr += x * x;
        }
        if (N == 0) {
            System.out.println("No Data");
            return;
        }
        avgX = sumX / N;
        avgY = sumY / N;
        float beta1 = (sumXY - N * avgX * avgY) / (sumXSqr - N * avgX * avgX);
        float beta0 = avgY - beta1 * avgX;
        List<Integer> estimationsY = new ArrayList<>(planedX.size());
        for (int i = 0, planedXSize = planedX.size(); i < planedXSize; i++) {
            Integer x = planedX.get(i);
            estimationsY.add(Math.round(beta0 + beta1 * x));
        }
        float sigmaSum = 0;
        float sumVariant = 0;
        for (int i = 0, floatArraysLength = floatArrays.length; i < floatArraysLength; i++) {
            List<Float> floatArray = floatArrays[i];
            if (floatArray.size() != 2) {
                N--;
                continue;
            }
            float x = floatArray.get(0);
            float y = floatArray.get(1);
            float tmp = (y - beta0 - beta1 * x);
            sumVariant += tmp * tmp;
            sigmaSum += (x - avgX) * (x - avgX);
        }
        float variance = (1 / (N - 2)) * sumVariant;
        float stdDerivation = (float) Math.sqrt(variance);//sigma?
        System.out.println("regression is " + beta1);
        System.out.println("Beta_0 is " + beta0);
        System.out.println("Sigma is " + stdDerivation);
        float t90 = 1.860f;
        float t70 = 1.108f;
        for (int i = 0, planedXSize = planedX.size(); i < planedXSize; i++) {
            int planX = planedX.get(i);
            int estimatedY = estimationsY.get(i);
            System.out.println("Planned LOC = " + planX + ";\tEstimated Y = " + estimatedY);
            float range = (float) (t90 * stdDerivation * Math.sqrt(1 + 1 / N + Math.pow((planX - avgX), 2) / sigmaSum));
            System.out.print("Range(90) is " + range);
            int UPI = estimatedY + Math.round(range);
            int LPI = estimatedY - Math.round(range);
            System.out.print(";\tUPI is " + UPI);
            System.out.println(";\tLPI is " + LPI);
            range = (float) (t70 * stdDerivation * Math.sqrt(1 + 1 / N + Math.pow((planX - avgX), 2) / sigmaSum));
            System.out.print("Range(70) is " + range);
            UPI = estimatedY + Math.round(range);
            LPI = estimatedY - Math.round(range);
            System.out.print(";\tUPI is " + UPI);
            System.out.println(";\tLPI is " + LPI + "\n");
        }
    }

    private static List<Float>[] getFloatArray(String file) {
        List<String> strings = readFile(file);
        List<Float> numbers = null;
        Float f = 0f;
        List<Float>[] floatArrays = new List[strings.size()];
        for (int i = 0, stringsSize = strings.size(); i < stringsSize; i++) {
            String s = strings.get(i);
            List<String> nums = Arrays.asList(s.split("[ ]+"));
            nums = nums.stream().filter(n -> n.matches("[-]?[1-9]{1}[0-9]*([\\.]{1}[0-9]*)?|0\\.[0-9]+|0")).collect(Collectors.toList());//nums is array of numbers in line
            if (nums.size() != K) {
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
        return floatArrays;
    }

    //Merge from http://howtodoinjava.com/algorithm/merge-sort-java-example/
    public static Comparable[] mergeSort(Comparable[] list) {
        if (list.length <= 1) {
            return list;
        }
        Comparable[] first = new Comparable[list.length / 2];
        Comparable[] second = new Comparable[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
        mergeSort(first);
        mergeSort(second);
        merge(first, second, list);
        return list;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;
        while (iFirst < first.length && iSecond < second.length) {
            if (first[iFirst].compareTo(second[iSecond]) < 0) {
                result[iMerged] = first[iFirst];
                iFirst++;
            } else {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }

}