package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private String expectedStylish;
    private String expectedStylish2;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    final void beforeEach() throws IOException {
        String expectedStylishFile = Objects.requireNonNull(classLoader
                        .getResource("result_stylish.txt"))
                .getFile();
        Path pathExpectedStylishFile = Paths.get(expectedStylishFile).toAbsolutePath().normalize();
        expectedStylish = Files.readString(pathExpectedStylishFile);
        String expectedStylishFile2 = Objects.requireNonNull(classLoader
                        .getResource("result_stylish2.txt"))
                .getFile();
        Path pathExpectedStylishFile2 = Paths.get(expectedStylishFile2).toAbsolutePath().normalize();
        expectedStylish2 = Files.readString(pathExpectedStylishFile2);
        String expectedPlainFile = Objects.requireNonNull(classLoader
                        .getResource("result_plain.txt"))
                .getFile();
        Path pathExpectedPlainFile = Paths.get(expectedPlainFile).toAbsolutePath().normalize();
        expectedPlain = Files.readString(pathExpectedPlainFile);
        String expectedJsonFile = Objects.requireNonNull(classLoader
                        .getResource("result_json.json"))
                .getFile();
        Path pathExpectedJsonFile = Paths.get(expectedJsonFile).toAbsolutePath().normalize();
        expectedJson = Files.readString(pathExpectedJsonFile);
    }

    @Test
    public void expectedStylishWithJsonTest() throws IOException {

        String formatName = "stylish";
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filePath1, filePath2, formatName);
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
    public void expectedStylishWithYamlTest() throws IOException {

        String formatName = "stylish";
        String filePath1 = "src/test/resources/file1.yaml";
        String filePath2 = "src/test/resources/file2.yaml";
        String actual = Differ.generate(filePath1, filePath2, formatName);
        Assertions.assertEquals(actual, expectedStylish);

    }

}
