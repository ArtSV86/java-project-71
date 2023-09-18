package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public class Json {
    public static String differenceToJson(List<Map<String, Object>> differenceList) {
        String result = differenceList.toString();
        return result;

    }
}
