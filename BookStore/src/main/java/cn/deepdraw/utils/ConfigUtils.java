package cn.deepdraw.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigUtils {

	static Logger logger = LogManager.getLogger(ConfigUtils.class.getName());

	private static String filePath = "config.properties";

	public static String getProperties(String keyWord) {

		String value = "";
		try {

			Properties prop = PropertiesLoaderUtils.loadAllProperties(filePath);
			value = prop.getProperty(keyWord);
		} catch (IOException e) {

			logger.error(e);
		}
		return value;
	}
}