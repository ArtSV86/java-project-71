package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {

        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        List<Map<String, Object>> differenceList = TreeBuild.build(data1, data2);
        return Formatter.toString(differenceList, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String defineFormat(String filePath) {
        if (filePath.endsWith("json")) {
            return "json";
        } else if (filePath.endsWith("yaml")) {
            return "yaml";
        } else if (filePath.endsWith("yml")) {
            return "yml";
        } else {
            throw new RuntimeException("File '" + filePath + "' is in an unknown format");
        }
    }


    public static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileContent = Files.readString(path);
        String fileFormat = defineFormat(filePath);
        return Parser.toMap(fileContent, fileFormat);
    }
}
