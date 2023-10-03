package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TreeBuild {
    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> differenceList = new LinkedList<>();
        for (String key : keys) {
            Map<String, Object> node = new HashMap<>();
            node.put("key", key);
            Object val1 = (data1.get(key) == null ? "null" : data1.get(key));
            Object val2 = (data2.get(key) == null ? "null" : data2.get(key));
            if (!data2.containsKey(key)) {
                node.put("type", "removed");
                node.put("value1", val1);
                differenceList.add(node);
            } else if (!data1.containsKey(key)) {
                node.put("type", "added");
                node.put("value1", val2);
                differenceList.add(node);
            } else if (data1.containsKey(key) && data2.containsKey(key)
                    && !val1.equals(val2)) {
                node.put("type", "changed");
                node.put("value1", val1);
                node.put("value2", val2);
                differenceList.add(node);
            } else {
                node.put("type", "unchanged");
                node.put("value1", val1);
                differenceList.add(node);
            }
        }
        return differenceList;
    }
}
