package pmtable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configurationLoader {

	private static final String CONFIG_FILE_PATH = "config.properties";
    private static Properties properties;

    static {
        properties = loadConfig(CONFIG_FILE_PATH);
    }

    public static Properties getProperties() {
        return properties;
    }

    private static Properties loadConfig(String configFilePath) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
            return properties;
        } catch (IOException e) {
            System.err.println("Failed to load configuration from file: " + configFilePath);
            e.printStackTrace();
            System.exit(1); // Terminate the program
            return null;    // This line will never be reached, but required for compilation
        }
    }
}
