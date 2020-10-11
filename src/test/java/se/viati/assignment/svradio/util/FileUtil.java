package se.viati.assignment.svradio.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtil {

    private static String resourcePath = "src/test/resources/%s";
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String readAsString(String fileName) throws IOException {
        return Files.readString(Path.of(String.format(resourcePath, fileName)));
    }

    public static <T> T parseJSONToGivenResponseType(String fileName, Class<T> responseType) {
        try {
            return objectMapper.readValue(readAsString(fileName), responseType);
        } catch (IOException e) {
            log.error("Error in reading/parsing file {} due to {}", fileName, e);
        }
        return null;
    }
}
