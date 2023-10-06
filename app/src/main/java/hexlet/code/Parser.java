package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> toMap(String fileContent, String fileFormat) throws JsonProcessingException {
        switch (fileFormat) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(fileContent, new TypeReference<>() {
                });
            }

            case "yaml", "yml" -> {
                ObjectMapper mapper = new YAMLMapper();
                return mapper.readValue(fileContent, new TypeReference<>() {
                });
            }

            default -> throw new RuntimeException("Unexpected format: " + fileFormat);
        }
    }
}
