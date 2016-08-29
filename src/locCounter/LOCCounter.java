package locCounter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nettema on 24.08.2016.
 */
public class LOCCounter {
    File file;

    public LOCCounter(File file) {
        this.file = file;
    }

    public int linesOfCodeCount() {
        int counter = 0;
        List<String> strings = allStrings();
        if (strings.size() == 0) {
            return counter;
        }
        String temp;
        Boolean isBlockComment = false;
        for (int i = 0; i < strings.size(); i++) {
            temp = strings.get(i);
            if (temp.isEmpty() || temp.startsWith("//")) {
                continue;
            }

            //simplest case
            if (temp.startsWith("/*")) {
                while (!temp.contains("*/")) {
                    i++;
                    temp = strings.get(i);
                }
                if (temp.matches(".*(\\*\\/).+$")) {
                    counter++;
                }
                continue;
            }

            if (temp.matches("^[^\"]+(\\/\\*)")) {
                if (temp.matches(".*(\\*\\/).+$")) {
                    counter++;
                    continue;
                }
                counter++;
                while (!temp.contains("*/")) {
                    i++;
                    temp = strings.get(i);
                }
                if (temp.matches(".*(\\*\\/).+$")) {
                    counter++;
                }
                continue;
            }
            counter++;
        }
        return counter;
    }

    private List<String> allStrings() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> strings = new LinkedList<>();
        if (reader == null) {
            return strings;
        }
        try {
            while (reader.ready()) {
                strings.add(reader.readLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

}
