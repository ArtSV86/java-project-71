package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static String expectedStylish;
    private static String expectedStylish2;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void beforeAll() throws IOException {

        expectedStylish = read("result_stylish.txt");
        expectedStylish2 = read("result_stylish2.txt");
        expectedPlain = read("result_plain.txt");
        expectedJson = read("result_json.json");
    }


    public static String read(String fileName) throws IOException {

        String expectedFile = "./src/test/resources/" + fileName;
        return Files.readString(Paths.get(expectedFile));
    }

    @Test
    public void expectedStylishWithJsonTestAndWithoutFormatName() throws IOException {

        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filePath1, filePath2);
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void expectedStylishTwoWithJsonTest() throws IOException {

        String formatName = "stylish";
        String filePath1 = "src/test/resources/file3.json";
        String filePath2 = "src/test/resources/file4.json";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedStylish2);
    }

    @Test
    public void expectedPlainTest() throws IOException {
        String formatName = "plain";
        String filePath1 = "src/test/resources/file3.json";
        String filePath2 = "src/test/resources/file4.json";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedPlain);
    }

    @Test
    public void expectedJsonTest() throws IOException {
        String formatName = "json";
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedJson);
    }

    @Test
    public void expectedStylishWithYamlTestAndWithoutFormatName() throws IOException {

        String filePath1 = "src/test/resources/file1.yaml";
        String filePath2 = "src/test/resources/file2.yaml";
        String actual = Differ.generate(filePath1, filePath2);
        Assertions.assertEquals(actual, expectedStylish);

    }

    @Test
    public void expectedStylishTwoWithYamlTest() throws IOException {

        String formatName = "stylish";
        String filePath1 = "src/test/resources/file3.yaml";
        String filePath2 = "src/test/resources/file4.yaml";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedStylish2);
    }
}
