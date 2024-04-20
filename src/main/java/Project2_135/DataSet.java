package Project2_135;


import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;
import java.util.stream.Collectors;

public class DataSet {
    private final TreeSet<String> set = new TreeSet<String>();
    private final SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);


    public void addData(String data){
        if(data == null || data.isEmpty()){
            return;
        }
        set.add(data);
        graph.addVertex(data);
    }

    public void connectVertices() {
        for (String vertex1 : set) {
            for (String vertex2 : set) {
                if (!vertex1.equals(vertex2) && isOneCharDiffOrShifted(vertex1, vertex2)) {
                    graph.addEdge(vertex1, vertex2);
                }
            }
        }
    }

    private boolean isOneCharDiffOrShifted(String word1, String word2) {

        // Check one char diff
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }

        if (diffCount == 1) {
            return true;
        }

        // Check left shift
        String leftShiftedWord1 = word1.substring(1) + word1.charAt(0);

        // Check right shift
        String rightShiftedWord1 = word1.charAt(word1.length() - 1) + word1.substring(0, word1.length() - 1);

        return leftShiftedWord1.equals(word2) || rightShiftedWord1.equals(word2);
    }

    public Set<String> regularExpression(String regex){
        return set.stream()
                .filter(s -> s.toLowerCase().startsWith(regex.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
