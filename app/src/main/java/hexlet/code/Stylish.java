package hexlet.code;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String differenceToStylish(List<Map<String, Object>> differenceList) {
        StringBuilder result = new StringBuilder("{\n");
        for (var map : differenceList) {
            Object value1 = map.get("value1");
            Object value2 = map.get("value2");
            Object key = map.get("key");
            switch ("type") {
                case "changed" -> {
                    result.append("- ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n")
                            .append("+ ")
                            .append(key)
                            .append(": ")
                            .append(value2)
                            .append("\n");
                }
                case "added" -> {
                    result.append("+ ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                }
                case "removed" -> {
                    result.append("- ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                }
                default -> {
                    result.append("  ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                }
            }


        }
        return result.append("}").toString();
    }

}