package hexlet.code;

import java.util.Map;
import java.util.List;

public class Json {
    public static String differenceToJson(List<Map<String, Object>> differenceList) {
        StringBuilder result = new StringBuilder("{\n");
        var size = differenceList.size();
        var index = 0;
        for (var item : differenceList) {
            var value1 = item.get("value1");
            var value2 = item.get("value2");
            var key = item.get("key");
            switch ("type") {
                case "changed" -> {
                    result.append("  ")
                            .append(key)
                            .append(": ")
                            .append(value1);
                    result.append("\n");
                    result.append("  ")
                            .append(key)
                            .append(": ")
                            .append(value2);
                }
                case "added" -> result.append("+ ")
                        .append(key)
                        .append(": ")
                        .append(value1);
                case "removed" -> result.append("- ")
                        .append(key)
                        .append(": ")
                        .append(value1);
                default -> result.append("  ")
                        .append(key)
                        .append(": ")
                        .append(value1);
            }
            index++;
            if (index == size) {
                result.append("\n");
            }

        }
        return result.append("}").toString();
    }
}
