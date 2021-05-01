package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
    private static Properties properties;
    private static File file;

    /**
     * Initial config properties
     */
    static {
        properties = new Properties();
        try {
            file = new File(ConfigHelper.class.getClassLoader().getResource("config.properties").toURI());
            InputStream config = new FileInputStream(file);
            properties.load(config);
            config.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static void setValue(String key, String value) {
        try {
            FileOutputStream config = new FileOutputStream(file);
            properties.setProperty(key, value);
            properties.store(config,"");
            config.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
