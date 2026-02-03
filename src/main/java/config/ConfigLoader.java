package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static Config load() {
        Properties props = new Properties();
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("Missing config.properties in src/test/resources");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }

        String baseUrl = props.getProperty("baseUrl", "https://demoqa.com").trim();
        int timeout = Integer.parseInt(props.getProperty("defaultTimeoutSeconds", "10").trim());
        return new Config(baseUrl, timeout);
    }
}
