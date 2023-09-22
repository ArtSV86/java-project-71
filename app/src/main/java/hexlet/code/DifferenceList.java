package hexlet.code;

import java.util.*;

public class DifferenceList {
    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> differenceList = new LinkedList<>();
        for (String key : keys) {
            Map<String, Object> node = new HashMap<>();
            Object val1 = (data1.get(key) == null ? "null" : data1.get(key));
            Object val2 = (data2.get(key) == null ? "null" : data2.get(key));
            if (!data2.containsKey(key)) {
//                Map<String, Object> node = Map.of("type", "removed", "key", key,
//                        "value1", data1.get(key));
                node.put("key", key);
                node.put("type", "removed");
                node.put("value1", val1);
                differenceList.add(node);
            } else if (!data1.containsKey(key)) {
// Map<String, Object> node = Map.of("type", "added", "key", key, "value1", data2.get(key));
                node.put("key", key);
                node.put("type", "added");
                node.put("value1", val2);
                differenceList.add(node);
            } else if (data1.containsKey(key) && data2.containsKey(key)
                    && !val1.equals(val2)) {
//                Map<String, Object> node = Map.of("type", "changed", "key", key,
//                        "value1", val1, "value2", val2);
                node.put("key", key);
                node.put("type", "changed");
                node.put("value1", val1);
                node.put("value2", val2);
                differenceList.add(node);
            } else {
//                Map<String, Object> node = Map.of("type", "unchanged", "key", key,
//                        "value1", data1.get(key));
                node.put("key", key);
                node.put("type", "unchanged");
                node.put("value1", val1);
                differenceList.add(node);
            }
        }
        return differenceList;
    }
}
