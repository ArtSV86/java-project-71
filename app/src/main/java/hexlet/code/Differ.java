package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path normalize1 = Paths.get("src/main/resources/file1.json").toAbsolutePath().normalize();
        String fileContent1 = Files.readString(normalize1);
        Path normalize2 = Paths.get("src/main/resources/file1.json").toAbsolutePath().normalize();
        String fileContent2 = Files.readString(normalize2);
        Map<String, Object> data1 = objectMapper.readValue(fileContent1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> data2 = objectMapper.readValue(fileContent2, new TypeReference<Map<String, Object>>() {
        });
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> answer = new LinkedList<>();
        for(String key: keys) {
            if (!data2.containsKey(key)) {

                Map<String, Object> node = Map.of("type", "removed", "key", key, "value", data1.get(key));
                answer.add(node);
            } else if (!data1.containsKey(key)) {
                Map<String, Object> node = Map.of("type", "added", "key", key, "value", data2.get(key));
                answer.add(node);
            } else if (!data1.get(key).equals(data2.get(key))) {
                Map<String, Object> node = Map.of("type", "changed", "key", key, "value", data1.get(key), "value2", data2.get(key));
                answer.add(node);
            } else {
                Map<String, Object> node = Map.of("type", "unchanged", "key", key, "value", data1.get(key));
                answer.add(node);
            }
        }
        return answer.toString().replace("},",  "\n").replace("=" ,": ").replace("{", "{ \n ").replace("}", "\n}");
        }
}
//        for (Map.Entry<String, Object> entry1 : data1.entrySet()) {
//            for (Map.Entry<String, Object> entry2 : data2.entrySet()) {
//                if (entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals(entry2.getValue())) {
//                    if (!result.containsValue(entry1.getValue())) {
//                        result.put("  " + entry1.getKey(), entry1.getValue());
//                    }
//                } else if (entry1.getKey().equals(entry2.getKey()) && !entry1.getValue().equals(entry2.getValue())) {
//                    if (!result.containsValue(entry2.getValue())) {
//                        result.put("+ " + entry2.getKey(), entry2.getValue());
//                    }
//                    if (!result.containsValue(entry1.getValue())) {
//                        result.put("- " + entry1.getKey(), entry1.getValue());
//                    }
//                } else if (!entry1.getKey().equals(entry2.getKey()) && !entry1.getValue().equals(entry2.getValue())) {
//                    if (!result.containsValue(entry1.getValue())) {
//                        result.put("- " + entry1.getKey(), entry1.getValue());
//                    }
//                    if (!result.containsValue(entry2.getValue())) {
//                        result.put("+ " + entry2.getKey(), entry2.getValue());
//                    }
//                }
//            }
//
//        }
//        return result.toString().replace(',',  '\n').replace("=" ,": ").replace("{", "{ \n ").replace("}", "\n}");
//    }