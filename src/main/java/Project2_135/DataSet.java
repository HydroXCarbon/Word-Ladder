package Project2_135;


import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
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
        for (String vertex1 : graph.vertexSet()) {
            for (String vertex2 : graph.vertexSet()) {
                if (!vertex1.equals(vertex2) && (isOneCharDiffOrShifted(vertex1, vertex2) != 0)) {
                    graph.addEdge(vertex1, vertex2);
                }
            }
        }
    }

    public List<String> findShortestPath(String startVertex, String targetVertex) {
        BFSShortestPath<String, DefaultEdge> bfs = new BFSShortestPath<>(graph);
        GraphPath<String, DefaultEdge> path = bfs.getPath(startVertex, targetVertex);

        if (path == null) {
            return Collections.emptyList();
        }

        return path.getVertexList();
    }

    public int findCost(String word1, String word2){
        int i;
        for (i = 0; i < word1.length(); i++) {
            if (Character.toLowerCase(word1.charAt(i)) != Character.toLowerCase(word2.charAt(i))) {
                break;
            }
        }
        return Math.abs(Character.toLowerCase(word1.charAt(i)) - Character.toLowerCase(word2.charAt(i)));
    }


    public int isOneCharDiffOrShifted(String word1, String word2) {

        // Check one char diff
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }

        if (diffCount == 1) {
            return 1;
        }

        // Check left shift
        String leftShiftedWord1 = word1.substring(1) + word1.charAt(0);

        // Check right shift
        String rightShiftedWord1 = word1.charAt(word1.length() - 1) + word1.substring(0, word1.length() - 1);

        if(leftShiftedWord1.equals(word2) || rightShiftedWord1.equals(word2)){
            return 2;
        }
        return -1;
    }

    public Set<String> regularExpression(String regex){
        return graph.vertexSet().stream()
                .filter(s -> s.toLowerCase().startsWith(regex.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
