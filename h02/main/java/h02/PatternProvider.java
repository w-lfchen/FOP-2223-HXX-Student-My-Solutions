package h02;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PatternProvider {

    private final boolean[][] pattern;

    public PatternProvider(String filename) throws IOException {
        final @Nullable InputStream patternStream = getClass().getResourceAsStream("/" + filename);
        if (patternStream == null) {
            throw new IOException("Could not find file " + filename + " in src/main/resources");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(patternStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }

        String[] patternAsStrings = sb.toString().replaceAll(" ", "").split("\n");

        this.pattern = stringsToPattern(patternAsStrings);
    }

    private boolean[][] stringsToPattern(String[] patternAsStrings) {
        boolean[][] pattern = new boolean[patternAsStrings[0].length()][patternAsStrings.length];

        for (int y = patternAsStrings.length - 1; y >= 0; y--) {
            char[] chars = patternAsStrings[y].toCharArray();
            for (int x = 0; x < patternAsStrings[y].length(); x++) {
                pattern[x][patternAsStrings.length - 1 - y] = chars[x] == '1';
            }
        }

        return pattern;
    }

    public boolean[][] getPattern() {
        return pattern;
    }

}
