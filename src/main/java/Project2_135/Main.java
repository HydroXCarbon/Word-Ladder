package Project2_135;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = "src/main/java/Project2_135/words/";
        boolean fileFound = false;
        while(!fileFound) {
            try {
                String filename = scanner.nextLine();
                Scanner fileScanner = new Scanner(new File(path + filename));
                storeData(fileScanner);
                fileFound = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void storeData(Scanner file){
        
    }
}