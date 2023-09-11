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
        String fileContent1 = Files.readString(Paths.get(filePath1).toAbsolutePath().normalize());
        String fileContent2 = Files.readString(Paths.get(filePath2).toAbsolutePath().normalize());
        String fileFormat1 = defineFormat(filePath1);
        String fileFormat2 = defineFormat(filePath2);
        Map<String, Object> data1 = Parser.toMap(fileContent1, fileFormat1);
        Map<String, Object> data2 = Parser.toMap(fileContent2, fileFormat2);
        List<Map<String, Object>> differenceList = DifferenceList.build(data1, data2);
        return Formatter.toString(differenceList, formatName);
    }
    public static String defineFormat(String filePath) {
        if (filePath.endsWith("json")) {
            return "json";
        } else if (filePath.endsWith("yml")) {
            return "yml";
        } else {
            throw new RuntimeException("File '" + filePath + "' is in an unknown format");
        }
    }
}
//        for (String key : keys) {
//            if (!data2.containsKey(key)) {
//                Map<String, Object> node = Map.of("- " + key, data1.get(key));
//                result.add(node);
//            } else if (!data1.containsKey(key)) {
//                Map<String, Object> node = Map.of("+ " + key, data2.get(key));
//                result.add(node);
//            } else if (!data1.get(key).equals(data2.get(key))) {
//                Map<String, Object> node = Map.of("- " + key, data1.get(key), "+ " + key, data2.get(key));
//                result.add(node);
//            } else {
//                Map<String, Object> node = Map.of("  " + key, data1.get(key));
//                result.add(node);
//            }
//        }
//        return result.toString().replace("},", "").replace("=", ": ").replace("{", " \n ").replace("}", "\n}").replace(",", " \n").replace("[", "{").replace("}]", "}");
//    }
//}
