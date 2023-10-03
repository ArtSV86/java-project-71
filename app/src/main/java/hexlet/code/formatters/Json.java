package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.List;

public class Json {
    public static String differenceToJson(List<Map<String, Object>> differenceList) {

        try {
            return new ObjectMapper().writeValueAsString(differenceList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
