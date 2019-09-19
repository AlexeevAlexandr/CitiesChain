package example.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final String inputFile = "src/main/resources/input.txt";
    private static final String outputFile = "src/main/resources/output.txt";

    public static void main(String[] args) throws IOException {

        List<String> availableCities = Files.readAllLines(Paths.get(inputFile));
        List<String> citiesList = Solver.solve(availableCities);
        new Writer().write(outputFile, citiesList);

        int a = String.join("", citiesList).length();
        System.out.println(a);
    }
}
