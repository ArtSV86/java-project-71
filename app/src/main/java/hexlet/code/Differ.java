package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


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
        } else if (filePath.endsWith("yaml")) {
            return "yaml";
        } else {
            throw new RuntimeException("File '" + filePath + "' is in an unknown format");
        }
    }
}
