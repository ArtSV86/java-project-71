package hexlet.code;

import hexlet.code.Differ.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
          //  paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String formatName;
    @Option(names = {"-h", "--help"},
            description = "Show this help message and exit.")
    private String help;
    @Option(names = {"-v", "--version"},
            description = "Print version information and exit.")
    private String version;
    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first file")
    private String filePath1;
    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second file")
    private String filePath2;
    @Override
    public Integer call() {

        try {
            String formattedDiff = Differ.generate(filePath1, filePath2, formatName);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}