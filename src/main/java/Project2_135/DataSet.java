package Project2_135;


import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    public void connectVertices(){
        List<String> vertices = new ArrayList<>(set);
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                String vertex1 = vertices.get(i);
                String vertex2 = vertices.get(j);
                if (shareLetter(vertex1, vertex2)) {
                    graph.addEdge(vertex1, vertex2);
                }
            }
        }
    }

    private boolean shareLetter(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            if (word2.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    public Set<String> regularExpression(String regex){
        return set.stream()
                .filter(s -> s.toLowerCase().startsWith(regex.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
