package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties getProperties() {
        return prop;
    }

    public static void setProp(Properties prop) {
        PropertiesLoader.prop = prop;
    }

    public static void setProperty(String key, String value) throws IOException {
        if (getProperty(key) != null) {
            getProperties().setProperty(key, value);
        }
    }

    static Properties prop, prop2, merged;
    static String liveProperties = "src/main/resources/live.properties";
    static String testProperties = "src/main/resources/test.properties";

    public static String getProperty(String key) {
        if (getProperties() == null) {
            loadPropertyFile();
        }

        return merged.getProperty(key);
    }

    private static void loadPropertyFile() {
        prop = new Properties();
        prop2 = new Properties();
        merged = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/framework.properties");
            prop2.load(input);
            merged.putAll(prop2);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String s = System.getProperty("testRunEnvironment");
            if ("live".equals(s)) {
                input = new FileInputStream(liveProperties);

            } else if ("test".equals(s)) {
                input = new FileInputStream(testProperties);
//Defaulted to live for the HRTest
            } else {
                input = new FileInputStream(liveProperties);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
            merged.putAll(prop);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

