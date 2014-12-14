/**
 * 
 */
package com.gffny.ldrbrd.web.model.cache;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
abstract public class CacheFetcher<T> {
	public static final int NEVER_EXPIRE = 0;
	private String key;
	private boolean cacheNullValue;

	public CacheFetcher(String key) {
		this.key = key;
	}

	public CacheFetcher(String key, boolean cacheNullValue) {
		this.key = key;
		this.cacheNullValue = cacheNullValue;
	}

	public CacheFetcher(Class<?> clazz, String method) {
		this(clazz.getName() + "." + method);
	}

	public String getKey() {
		return key;
	}

	public boolean cacheNullValue() {
		return cacheNullValue;
	}

	public int getExpiration() {
		return NEVER_EXPIRE;
	}

	abstract public T fetch();
}
