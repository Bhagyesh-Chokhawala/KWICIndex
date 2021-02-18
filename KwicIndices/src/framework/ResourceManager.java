package framework;

import KwicIndices.MasterController;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ResourceManager {
    private static ResourceManager singleInstance = null;
    private static Properties prop = new Properties();
    public static ResourceManager getResources()
    {
        if (singleInstance == null)
            singleInstance = new ResourceManager();

        return singleInstance;
    }
    private ResourceManager() {
        try (InputStream input = MasterController.class.getClassLoader().getResourceAsStream("resources/Config.properties")) {

            prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
    public static <T> T getPropertyOrDefault(String key, T defaultValue) {
        try {
            T value = (T) prop.getProperty(key);
            return value == null ? (T) defaultValue : (T) value;
        }
        catch (Exception e)  {
            return defaultValue;
        }
    }
    public static List<String> getCommaSeperatedPropertyOrDefault(String key, String[] defaultValue) {
        String value = prop.getProperty(key);
        return value == null ? Arrays.asList(defaultValue) : Arrays.asList(value.split(","));
    }
    public static  int getPropertyOrDefault(String key, int defaultValue) {
        String value= getResources().getPropertyOrDefault(key,String.valueOf(defaultValue));
        return NumberUtils.toInt(value, defaultValue);
    }
    public static  Boolean getPropertyOrDefault(String key, Boolean defaultValue) {
        String value= getResources().getPropertyOrDefault(key,String.valueOf(defaultValue));
        return BooleanUtils.toBooleanDefaultIfNull(BooleanUtils.toBoolean(value), defaultValue);
    }
}
