package Project2_135;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DataSet {
    private final TreeSet<String> set = new TreeSet<String>();

    public void addData(String data){
        set.add(data);
    }

    public Set<String> regularExpression(String regex){
        return set.stream()
                .filter(s -> s.toLowerCase().startsWith(regex.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
