package utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while loading config.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
