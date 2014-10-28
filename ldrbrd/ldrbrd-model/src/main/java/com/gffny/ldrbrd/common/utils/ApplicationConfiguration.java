/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 */
public class ApplicationConfiguration {

	/**
	 * 
	 */
	protected final static Logger logger = Logger.getLogger(ApplicationConfiguration.class);

	/**
	 * 
	 */
	private static PropertiesConfiguration config;

	/**
	 * 
	 */
	private static String fileName = "leaderboard.properties";

	/** private constructor to hide the implicit public constructor */
	private ApplicationConfiguration() {

	}

	/**
	 * 
	 */
	static {
		String systemRoot = System.getProperty(Constant.PROPERTY_APPLICATION_ROOT);
		String path = FilenameUtils.concat(FilenameUtils.concat(systemRoot, "config"), fileName);

		try {
			if (StringUtils.isEmpty(path)) {
				config = new PropertiesConfiguration(fileName);
			} else {
				File externalFile = new File(path);
				if (externalFile.exists()) {
					config = new PropertiesConfiguration(externalFile);
				} else {
					config = new PropertiesConfiguration(fileName);
				}
			}

			if (config != null) {
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
			}

		} catch (ConfigurationException | SecurityException | NullPointerException ex) {
			logger.error("Unable to load configuration from: " + path, ex);
		}

		try {
			Properties buildProperties = new Properties();
			buildProperties.load(ApplicationConfiguration.class
					.getResourceAsStream("/build.properties"));
			addProperties(buildProperties);
		} catch (IOException | NullPointerException ex) {
			logger.error("Unable to load build info.");
		}

	}

	/**
	 * Application specific lookups to reduce property keys from being scattered through the
	 * application.
	 */
	public static boolean useMockEnvironment() {
		return true;
		// return getBoolean("local.environment");
	}

	/**
	 * @return
	 */
	public static String getLogDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"), "logs");
	}

	/**
	 * @return
	 */
	public static String getFileCacheDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"), "cache");
	}

	/**
	 * @return
	 */
	public static String getCodebaseDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"), "src");
	}

	/**
	 * @return
	 */
	public static String getConfigDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"), "config");
	}

	/**
	 * @return
	 */
	public static String getUserCookieName() {
		return getString("ldrbrd.cookie", "ldrbrd.cookie");
	}

	/**
	 * @return
	 */
	public static String getGolferIdHeaderName() {
		return getString("ldrbrd.golfer.id.header");
	}

	/**
	 * @return
	 */
	public static String getGolferNameHeaderName() {
		return getString("ldrbrd.golfer.name.header");
	}

	/**
	 * @return
	 */
	public static String getTestGolferId() {
		return getString("test.ldrbrd.golfer.id", "123");
	}

	/**
	 * @return
	 */
	public static String getTestGolferName() {
		return getString("test.ldrbrd.golfer.name", "Gaffney");
	}

	/**
	 * @return
	 */
	public static String getBuildNumber() {
		return getString("ldrbrd.build.number", "0");
	}

	/**
	 * @return
	 */
	public static String getRevisionNumber() {
		return getString("ldrbrd.revision.number", "0");
	}

	/**
	 * @return
	 */
	public static String getCompetitionRoundIdHeaderName() {
		return getString("ldrbrd.competitionroundid.header.name");
	}

	/**
	 * @return
	 */
	public static String getIPhoneAppDownloadLink() {
		return config.getString("mobile.iphone.app.download.link");
	}

	/**
	 * @return
	 */
	public static boolean isProductionEnvironment() {
		return getBoolean("production.environment", false);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getCompetitionVisibilityList() {
		return config.getList("ldrbrd.competition.visibility.list",
				CollectionUtils.asList("Society"));
	}

	/**
	 * @return
	 */
	public static String getStaticAssetPrefix() {
		return getString("static.asset.prefix", "/leaderboard");
	}

	/**
	 * @return
	 */
	public static String getAmazonContentUrlPrefixBuildEnv() {
		return StringUtils.joinPath(getAmazonUrlPrefix(), getAmazonContentBucketBuildEnv());
	}

	/**
	 * @return
	 */
	public static String getAmazonUrlPrefix() {
		return getString("amazon.url.prefix");
	}

	/**
	 * @return
	 */
	public static String getAmazonContentBucket() {
		return getString("amazon.content.bucket");
	}

	/**
	 * @return
	 */
	public static String getAmazonContentUrlPrefix() {
		return StringUtils.joinPath(getAmazonUrlPrefix(), getAmazonContentBucket());
	}

	/**
	 * @return
	 */
	public static String getAmazonContentBucketBuildEnv() {
		return getString("amazon.content.bucket.build.env");
	}

	/**
	 * @return
	 */
	public static String getAmazonUsername() {
		return getString("amazon.user");
	}

	/**
	 * @return
	 */
	public static String getAmazonPassword() {
		return getDecryptedString("amazon.pass");
	}

	/**
	 * @return
	 */
	public static String getAmazonAccessKey() {
		return getDecryptedString("amazon.access.key");
	}

	/**
	 * @return
	 */
	public static String getAmazonSecretKey() {
		return getDecryptedString("amazon.secret.key");
	}

	/**
	 * @param keys
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getStringFromKeys(String... keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}

		for (String key : keys) {
			String value = getString(key, null);

			if (StringUtils.isNotBlank(value)) {
				return value;
			}
		}

		return null;
	}

	/**
	 * @return
	 */
	public static String getBuildDescription() {
		List<String> parts = new ArrayList<String>();

		String buildNumber = getBuildNumber();

		if (StringUtils.isNotBlank(buildNumber)) {
			parts.add("b" + buildNumber);
		} else {
			String revisionNumber = getRevisionNumber();

			if (StringUtils.isNotBlank(revisionNumber)) {
				parts.add("r" + revisionNumber);
			}
		}

		if (useMockEnvironment()) {
			parts.add("local");
		}

		String buildDescription = StringUtils.join(parts, "-");

		return StringUtils.isNotBlank(buildDescription) ? buildDescription : "unknown";
	}

	/**
	 * Delegate methods which pass the request down to the underlying implementation
	 */
	public static void addProperty(String key, Object value) {
		if (StringUtils.isNotBlank(key)) {
			config.addProperty(key, value);
		}
	}

	/**
	 * @param properties
	 */
	public static void addProperties(Map<?, Object> properties) {
		if (MapUtils.isEmpty(properties)) {
			return;
		}

		for (Entry<?, Object> entry : properties.entrySet()) {
			addProperty(StringUtils.valueOf(entry.getKey()), entry.getValue());
		}
	}

	/**
	 * 
	 */
	public static void clear() {
		config.clear();
	}

	/**
	 * @param key
	 */
	public static void clearProperty(String key) {
		config.clearProperty(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static boolean containsKey(String key) {
		return config.containsKey(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean containsValue(String key, String value) {
		List<?> ids = getList(key);
		for (Iterator<?> i = ids.iterator(); i.hasNext();) {
			String s = (String) i.next();
			if (StringUtils.isEquivalent(s, value)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param key
	 * @return
	 */
	public static BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
		return config.getBigDecimal(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
		return config.getBigInteger(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static byte getByte(String key) {
		return config.getByte(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static byte getByte(String key, byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Byte getByte(String key, Byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static double getDouble(String key) {
		return config.getDouble(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static double getDouble(String key, double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Double getDouble(String key, Double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static float getFloat(String key) {
		return config.getFloat(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static float getFloat(String key, float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Float getFloat(String key, Float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return config.getInt(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInteger(String key, Integer defaultValue) {
		return config.getInteger(key, defaultValue);
	}

	/**
	 * @return
	 */
	public static Iterator<?> getKeys() {
		return config.getKeys();
	}

	/**
	 * @param prefix
	 * @return
	 */
	public static Iterator<?> getKeys(String prefix) {
		return config.getKeys(prefix);
	}

	/**
	 * @param key
	 * @return
	 */
	public static List<?> getList(String key) {
		return config.getList(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static List<?> getList(String key, List<?> defaultValue) {
		return config.getList(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		return config.getLong(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static long getLong(String key, long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Long getLong(String key, Long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static Properties getProperties(String key) {
		return config.getProperties(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static Object getProperty(String key) {
		return config.getProperty(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static short getShort(String key) {
		return config.getShort(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static short getShort(String key, short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Short getShort(String key, Short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return config.getString(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public static String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getDecryptedString(String key) {
		String encrypted = getString(key);
		return Security.decrypt(encrypted);
	}

	/**
	 * @param key
	 * @param defaultEncryptedValue
	 * @return
	 */
	public static String getDecryptedString(String key, String defaultEncryptedValue) {
		String encrypted = getString(key, defaultEncryptedValue);
		return Security.decrypt(encrypted);
	}

	/**
	 * @return
	 */
	public static boolean isEmpty() {
		return config.isEmpty();
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, Object value) {
		config.setProperty(key, value);
	}

	/**
	 * @param prefix
	 * @return
	 */
	public static Configuration subset(String prefix) {
		return config.subset(prefix);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(String key, Boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}
}
