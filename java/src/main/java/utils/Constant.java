package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-11 23:14
 */
public class Constant {
    public static final String AppFile = "/application.properties";

    private static Properties appProperties;

    static {
        appProperties = loadPropertiesFromFile(AppFile);
    }

    public static Properties getAppProperties() {
        return appProperties;
    }

    private static Properties loadPropertiesFromFile(String fileName) {
        Properties properties = new Properties();
        try (InputStream is = Constant.class.getResourceAsStream(fileName)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
