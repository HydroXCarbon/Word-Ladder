package Project2_135;


import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;
import java.util.stream.Collectors;

public class DataSet {
    private final SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);


    public void addData(String data){
        if(data == null || data.isEmpty()){
            return;
        }
        graph.addVertex(data);
    }

    public void connectVertices() {
        List<String> vertices = new ArrayList<>(graph.vertexSet());
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                String vertex1 = vertices.get(i);
                String vertex2 = vertices.get(j);
                if (isOneCharDiffOrShifted(vertex1, vertex2)) {
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
        return graph.vertexSet().stream()
                .filter(s -> s.toLowerCase().startsWith(regex.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
