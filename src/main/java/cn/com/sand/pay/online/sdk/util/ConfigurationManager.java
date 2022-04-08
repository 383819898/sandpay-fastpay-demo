package cn.com.sand.pay.online.sdk.util;

import java.io.IOException;

public class ConfigurationManager extends com.netflix.config.ConfigurationManager {
	public static void loadProperties(String[] configNames) throws IOException {
		for (int i = 0; i < configNames.length; i++) {
			loadAppOverrideProperties(configNames[i]);
		}
	}
}
