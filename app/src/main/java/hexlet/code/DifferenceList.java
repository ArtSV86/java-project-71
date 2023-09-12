package hexlet.code;

import java.util.*;

public class DifferenceList {
    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> differenceList = new LinkedList<>();
        for (String key : keys) {
            if (!data2.containsKey(key)) {

                Map<String, Object> node = Map.of("type", "removed", "key", key, "value1", data1.get(key));
                differenceList.add(node);
            } else if (!data1.containsKey(key)) {
                Map<String, Object> node = Map.of("type", "added", "key", key, "value1", data2.get(key));
                differenceList.add(node);
            } else if (!data1.get(key).equals(data2.get(key))) {
                Map<String, Object> node = Map.of("type", "changed", "key", key, "value1", data1.get(key), "value2", data2.get(key));
                differenceList.add(node);
            } else {
                Map<String, Object> node = Map.of("type", "unchanged", "key", key, "value1", data1.get(key));
                differenceList.add(node);
            }
        }
        return differenceList;
    }
}
