package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This <ConfigReader> class reads data from the 'config.properties' file in
 * "src/test/resources/configs" folder
 */
public class ConfigReader {

	private static final String FILE_PATH = "src/test/resources/configs/config.properties";
	private static Properties p;

	public static String getTextValue(String key) {
		loadPropertiesFile();
		return p.getProperty(key);
	}

	public static void loadPropertiesFile() {
		p = new Properties();
		try {
			p.load(new FileInputStream(FILE_PATH));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean getBooleanValue(String key) {
		loadPropertiesFile();
		return Boolean.parseBoolean(getTextValue(key));
	}

	public static long getLongValue(String key) {
		loadPropertiesFile();
		return Long.parseLong(getTextValue(key));
	}

}
