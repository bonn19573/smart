package com.guorui.smart.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

	public static Properties loadProps(String fileName) throws IOException {
		Properties p = new Properties();
		InputStream is = null;

		is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		if (is == null) {
			throw new FileNotFoundException(fileName);
		}

		p.load(is);
		LOGGER.info("loaded properties file:{0}",fileName);

		return p;
	}

	public static String getString(Properties p, String key) {
		return getString(p, key, "");
	}

	private static String getString(Properties p, String key, String defaultValue) {
		String result = defaultValue;

		if (p != null && p.contains(key)) {
			result = p.getProperty(key);
		}

		return result;

	}

}
