package sum;

/**
 * Created by nettema on 05.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        int projectLOC = LOCCounter.countLOC(Menu.getFile());
        System.out.println(String.format("This program consists of %d lines of code", projectLOC));
    }
}
