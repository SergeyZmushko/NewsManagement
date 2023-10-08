package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataReader {
    public static List<String> readData(String fileName) {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)),
                StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e + "Exception reading from " + fileName);
        }
        return result;
    }
}
