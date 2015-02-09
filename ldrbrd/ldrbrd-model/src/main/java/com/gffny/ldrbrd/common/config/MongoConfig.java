/**
 * 
 */
package com.gffny.ldrbrd.common.config;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class MongoConfig {

	/** */
	@Value("${mongo.server.url}")
	private String serverUrl;

	/** */
	@Value("${mongo.server.port}")
	private String serverPort;

	/** */
	@Value("${mongo.db.username}")
	private String databaseUsername;

	/** */
	@Value("${mongo.db.password}")
	private String databasePassword;

	/** */
	@Value("${mongo.db.name}")
	private String databaseName;

	/**
	 * @return the serverUrl
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * @param serverUrl
	 *            the serverUrl to set
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * 
	 * @return
	 */
	public int getServerPortIntValue() {
		if (NumberUtils.isNumber(this.serverPort)) {
			return Integer.valueOf(serverPort).intValue();
		}
		return -1;
	}

	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the databaseUsername
	 */
	public String getDatabaseUsername() {
		return databaseUsername;
	}

	/**
	 * @param databaseUsername
	 *            the databaseUsername to set
	 */
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	/**
	 * @return the databasePassword
	 */
	public String getDatabasePassword() {
		return databasePassword;
	}

	/**
	 * @param databasePassword
	 *            the databasePassword to set
	 */
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
