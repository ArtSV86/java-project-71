package hexlet.code.formatters;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {
    public static String differenceToPlain(List<Map<String, Object>> differenceList) {
        StringBuilder result = new StringBuilder();
        for (var map : differenceList) {
            Object value1 = map.get("value1");
            Object value2 = map.get("value2");
            Object key = map.get("key");
            switch (String.valueOf(map.get("type"))) {
                case "changed" -> result.append("Property ")
                        .append("'")
                        .append(key)
                        .append("'")
                        .append(" was updated. From ")
                        .append(getValue(value1))
                        .append(" to ")
                        .append(getValue(value2))
                        .append("\n");
                case "added" -> result.append("Property ")
                        .append("'")
                        .append(key)
                        .append("'")
                        .append(" was added with value: ")
                        .append(getValue(value1))
                        .append("\n");
                case "removed" -> result.append("Property ")
                        .append("'")
                        .append(key).append("'")
                        .append(" was removed")
                        .append("\n");
                default -> {
                }
            }
        }
        return result.toString().trim();
    }

    private static String getValue(Object value) {
        var tempValue = Objects.toString(value);
        if (tempValue.charAt(0) == '[' && tempValue.charAt(tempValue.length() - 1) == ']'
                || tempValue.charAt(0) == '{' && tempValue.charAt(tempValue.length() - 1) == '}') {
            return "[complex value]";
        } else if (tempValue.equals("false") || tempValue.equals("true")
                || tempValue.equals("null") || StringUtils.isNumeric(tempValue)) {
            return tempValue;
        } else {
            return "'" + value + "'";
        }
    }
}
