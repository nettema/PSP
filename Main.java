package locCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nettema on 24.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter filename");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename;
            while (!FileUtil.exists(filename = reader.readLine())) {
                System.out.println("no such file");
            }
            LOCCounter counter = new LOCCounter(FileUtil.getFile(filename));
            System.out.println(counter.linesOfCodeCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
