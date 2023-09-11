package hexlet.code;

import java.util.*;

public class DifferenceList {
    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> differenceList = new LinkedList<>();
        for(String key: keys) {
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

//        List<Map<String, Object>> diffList = new ArrayList<>();
//        TreeSet<String> keySet = collectKeysInSet(map1, map2);
//        for (var key : keySet) {
//            Map<String, Object> diffMap = new HashMap<>();
//            if (map1.containsKey(key) && !map2.containsKey(key)) {
//                diffMap.put("FIELD", key);
//                diffMap.put("STATUS", "REMOVED");
//                diffMap.put("OLD_VALUE", map1.get(key));
//            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
//                diffMap.put("FIELD", key);
//                diffMap.put("STATUS", "ADDED");
//                diffMap.put("NEW_VALUE", map2.get(key));
//            } else if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
//                diffMap.put("FIELD", key);
//                diffMap.put("STATUS", "SAME");
//                diffMap.put("OLD_VALUE", map1.get(key));
//            } else {
//                diffMap.put("FIELD", key);
//                diffMap.put("STATUS", "UPDATED");
//                diffMap.put("OLD_VALUE", map1.get(key));
//                diffMap.put("NEW_VALUE", map2.get(key));
//            }
//            diffList.add(diffMap);
//        }
//        return diffList;
//    }
}
