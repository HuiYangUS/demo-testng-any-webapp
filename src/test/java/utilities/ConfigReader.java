package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final String FILE_PATH = "src/test/resources/configs/config.properties";
    private static Properties p;

    public static String getTextValue(String key){
        loadPropertiesFile();
        return p.getProperty(key);
    }

    public static void loadPropertiesFile(){
        p=new Properties();
        try {
            p.load(new FileInputStream(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
