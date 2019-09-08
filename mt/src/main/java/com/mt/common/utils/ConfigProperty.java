package com.mt.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取器
 */
public final class ConfigProperty {
	private static final Logger logger = LoggerFactory.getLogger(ConfigProperty.class); 
	private static Map<String, String> configPool = new HashMap<String, String>();

	public static void initConfigFilter(String prefix, Properties props) {
		String[] pres = prefix.split(",");
		for (Object key : props.keySet()) {
			String k = key.toString();
			for (String p : pres) {
				if (k.startsWith(p)) {
					configPool.put(k, props.getProperty(k));
				}
			}
		}
	}

	public static String getConfigValue(String key) {
		return configPool.get(key);
	}

	public static void setConfigValue(String key, String value) {
		if (configPool.containsKey(key) && StringUtil.isEmpty(value)) {
			configPool.put(key, value);
		}
	}

	public static ConfigProperty cpInst = null;
	
	private Properties ps = null;  

	private ConfigProperty() {
		init();
	}

	public static ConfigProperty getInst() {
		if (cpInst == null) {
			cpInst = new ConfigProperty();
		}
		return cpInst;
	}

	public Properties getProperties() {
		return ps;
	}

	public String getProperty(String name) {
		return ps.getProperty(name);
	}

	/**
	 * 初始化配置文件中的信息
	 */
	private synchronized void init() {
		if (ps != null) {
			return;
		}
		BufferedReader br = null; 
		try {
			ps = new Properties();			
			InputStream is = ConfigProperty.class.getClassLoader().getResourceAsStream("conf/config.properties");
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			ps.load(br);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			cpInst = null;
			ps = null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
			}	
		}
	}
}