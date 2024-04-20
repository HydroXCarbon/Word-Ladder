package Project2_135;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataSet set = new DataSet();
        String userChoice = "";
        boolean quit = false;
        String path = "src/main/java/Project2_135/words/";
        boolean fileFound = false;
        while(!fileFound) {
            try {
                System.out.print("Enter word file = ");
                String filename = scanner.nextLine();
                Scanner fileScanner = new Scanner(new File(path + filename));
                storeData(fileScanner,set);
                fileFound = true;
                fileScanner.close();
            } catch (Exception e) {
            }
        }

        while(!quit){
            System.out.println("\nEnter menu >> (S = search, L = ladder, Q = quit)");
            userChoice = scanner.nextLine();
            switch(userChoice.toLowerCase()){
                case "q":
                    quit = true;
                    break;
                case "s":
                    Search(set);
                    break;
                case "l":
                    Ladder(set);
                    break;
                default:
            }
        }
        System.out.println();
        scanner.close();
    }

    public static void storeData(Scanner fileScanner, DataSet set){
        while(fileScanner.hasNextLine()){
            String col = fileScanner.nextLine().trim();
            set.addData(col);
        }
        set.connectVertices();
    }

    public static void Search(DataSet set){
        System.out.println("\nSearch = ");
        Scanner scanner = new Scanner(System.in);
        String regex = scanner.nextLine();
        Set<String> filteredSet = set.regularExpression(regex);
        for(String each : filteredSet){
            System.out.print(each);
        }
    }

    public static void Ladder(DataSet set){
        Scanner scanner = new Scanner(System.in);
        String word1 = "";
        String word2 = "";
        while(word1.length() != 5) {
            System.out.println("Enter 5 letter word 1 = ");
            word1 = scanner.nextLine();
        }

        while(word2.length() != 5) {
            System.out.println("Enter 5 letter word 2 = ");
            word2 = scanner.nextLine();
        }

        // Check existing word in DataSet
        if(!(set.regularExpression(word1).size() != 1 || set.regularExpression(word2).size() != 1)){
            System.out.printf("Cannot transform %s into %s\n",word1, word2);
            return;
        }


    }
}