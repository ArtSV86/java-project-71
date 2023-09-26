package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String toString(List<Map<String, Object>> differenceList, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.differenceToStylish(differenceList);
            case "plain" -> Plain.differenceToPlain(differenceList);
            case "json" -> Json.differenceToJson(differenceList);
            default -> throw new RuntimeException("Unexpected format: " + formatName);
        };
    }
}
