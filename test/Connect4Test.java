import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

public class Connect4Test {
    private String buildInputSequence(String[] inputs) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (String input : inputs) {
            result.add(input);
        }
        return result.toString();
    }

    private String unifyLineEnds(String input) {
        input = input.replaceAll("\\r\\n", "\\$");
        input = input.replaceAll("\\n", "\\$");
        return input;
    }

    private void assertEqualLE(String expected, String actual) {
        assertEquals(unifyLineEnds(expected), unifyLineEnds(actual));
    }

    @Test
    public void testSimpleWin() throws IOException {
        String[] inputs = {"6", "0", "6", "1", "6", "2", "5", "3"};

        Scanner simulation = new Scanner(buildInputSequence(inputs));
        StringWriter result = new StringWriter();
        PrintWriter output = new PrintWriter(result);

        Connect4.play(
                simulation, output
        );

        assertEqualLE(Files.readString(Path.of("resources/simpleWin.txt")), result.toString());
    }

    @Test
    public void testTowerWin() throws IOException {
        String[] inputs = {"0", "6", "1", "6", "2", "6", "2", "6"};

        Scanner simulation = new Scanner(buildInputSequence(inputs));
        StringWriter result = new StringWriter();
        PrintWriter output = new PrintWriter(result);

        Connect4.play(
                simulation, output
        );

        assertEqualLE(Files.readString(Path.of("resources/towerWin.txt")), result.toString());
    }

    @Test
    public void testOTowerWin() throws IOException {
        String[] inputs = {"6", "0", "6", "1", "6", "0", "6", "1", "6"};

        Scanner simulation = new Scanner(buildInputSequence(inputs));
        StringWriter result = new StringWriter();
        PrintWriter output = new PrintWriter(result);

        Connect4.play(
                simulation, output
        );

        assertEqualLE(Files.readString(Path.of("resources/oTowerWin.txt")), result.toString());
    }
}
