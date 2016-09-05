package sum;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nettema on 05.09.2016.
 */
public class LOCCounter {
    public static int countLOC(File file) {
        if (file.isDirectory())
            return countDirectory(file);
        else
            return countFile(file);
    }

    private static int countDirectory(File file) {
        int sum = 0;
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        for (int i = 0, filesLength = files.length; i < filesLength; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                sum += countDirectory(f);
            } else
                sum += countFile(f);
        }
        return sum;
    }

    private static int countFile(File file) {
        int sum = 0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> strings = new LinkedList<>();
        try {
            while (reader.ready()) {
                strings.add(reader.readLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String temp;
        for (int i = 0; i < strings.size(); i++) {
            temp = strings.get(i);
            if (temp.isEmpty() || temp.startsWith("//")) {
                continue;
            }
            if (temp.startsWith("/*")) {
                while (!temp.contains("*/")) {
                    i++;
                    temp = strings.get(i);
                }
                if (temp.matches(".*(\\*\\/).+$")) {
                    sum++;
                }
                continue;
            }

            if (temp.matches("^[^\"]+(\\/\\*)")) {
                if (temp.matches(".*(\\*\\/).+$")) {
                    sum++;
                    continue;
                }
                sum++;
                while (!temp.contains("*/")) {
                    i++;
                    temp = strings.get(i);
                }
                if (temp.matches(".*(\\*\\/).+$")) {
                    sum++;
                }
                continue;
            }
            sum++;
        }
        return sum;
    }

}
