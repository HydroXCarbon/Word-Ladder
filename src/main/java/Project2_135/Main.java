package Project2_135;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataSet set = new DataSet();
        String userChoice = "";
        boolean quit = false;
        String path = "src/main/java/Project2_135/words/";
        boolean fileFound = false;
        while (!fileFound) {
            try {
                System.out.print("Enter word file = ");
                String filename = scanner.nextLine();
                Scanner fileScanner = new Scanner(new File(path + filename));
                storeData(fileScanner, set);
                fileFound = true;
                fileScanner.close();
            } catch (Exception e) {
                System.out.println("File not found.");
            }
        }

        while (!quit) {
            System.out.println("\nEnter menu >> (S = search, L = ladder, Q = quit)");
            userChoice = scanner.nextLine();
            switch (userChoice.toLowerCase()) {
                case "q":
                    quit = true;
                    break;
                case "s":
                    Search(set);
                    break;
                case "l":
                    Ladder(set);
                    break;
                case "c":
                    CheckEdge(set);
                    break;
                default:
            }
        }
        System.out.println();
        scanner.close();
    }

    public static void CheckEdge(DataSet set){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select node to show data: ");
        String userInput = scanner.nextLine();
        System.out.println(set.getEdge(userInput));
    }

    public static void storeData(Scanner fileScanner, DataSet set) {
        // Store data
        while (fileScanner.hasNextLine()) {
            String col = fileScanner.nextLine().trim();
            set.addData(col);
        }
        set.connectVertices();
    }

    public static void Search(DataSet set) {
        System.out.println("\nSearch = ");
        Scanner scanner = new Scanner(System.in);
        String regex = scanner.nextLine();
        Set<String> filteredSet = set.regularExpression(regex);
        if(filteredSet.isEmpty()){
            System.out.println("Word not found!!");
            return;
        }
        System.out.println("=== Available words ===");
        int counter = 0;
        List<String> list = new ArrayList<>(filteredSet);
        Collections.sort(list);
        for (String each : list) {
            System.out.print(each + "       ");
            counter++;
            if (counter == 10) {
                System.out.println();
                counter = 0;
            }
        }
        System.out.println();
    }

    public static void Ladder(DataSet set) {
        int totalCost = 0;
        int cost = 0;
        Scanner scanner = new Scanner(System.in);
        String word1 = "";
        String word2 = "";

        // Receive input from user
        while (word1.length() != 5) {
            System.out.println("Enter 5 letter word 1 (Source word) = ");
            word1 = scanner.nextLine().toLowerCase();
        }

        // Receive input from user
        while (word2.length() != 5) {
            System.out.println("Enter 5 letter word 2 (Target word)= ");
            word2 = scanner.nextLine().toLowerCase();
        }

        // Check existing word in DataSet
        if (!((set.regularExpression(word1).size() == 1 || set.regularExpression(word2).size() == 1) && (word1!=word2))) {
            System.out.printf("\nCannot transform %s into %s (Cannot find word)\n", word1, word2);
            return;
        }

        // Get the shortest path
        GraphPath<String, DefaultWeightedEdge> graph = set.findShortestPath(word1, word2);

        // Verify path
        if (graph == null) {
            System.out.printf("\nCannot transform %s into %s (Cannot find path)\n", word1, word2);
            return;
        }

        // Get Vertices and edge
        List<String> vertices = graph.getVertexList();
        List<DefaultWeightedEdge> edge = graph.getEdgeList();

        System.out.println();
        System.out.println(word1);

        // Loop print vertices and weight
        for (int i = 1; i < vertices.size() ; i++) {
            cost = set.getEdgeWeight(edge.get(i-1));
            if (cost > 0) {
                System.out.printf("%s (ladder   + %d)\n", vertices.get(i), cost);
            } else if (cost == 0) {
                System.out.printf("%s (elevator + %d)\n", vertices.get(i), cost);
            }
            totalCost += cost;
        }

        System.out.printf("\nTransformation cost = %d\n", totalCost);


    }
}