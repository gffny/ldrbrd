/**
 * 
 */
package com.gffny.ldrbrd.web.model.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class Cache {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Cache.class);

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	final private Map<String, Object> cache;

	/**
	 * 
	 */
	final Object NULL_OBJECT = new Object();

	/**
	 * 
	 */
	public Cache() {
		this(new HashMap<String, Object>());
	}

	/**
	 * 
	 * @param cacheImpl
	 */
	protected Cache(Map<String, Object> cacheImpl) {
		this.cache = cacheImpl;
	}

	/**
	 * 
	 * @param fetcher
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(CacheFetcher<T> fetcher) {
		if (!getCache().containsKey(fetcher.getKey())) {
			Object o = fetcher.fetch();
			// Certain fetchers may treat a null value as valid
			if (o == null && !fetcher.cacheNullValue()) {
				return null;
			}

			getCache().put(fetcher.getKey(), (o == null) ? NULL_OBJECT : o);
		}

		Object value = getCache().get(fetcher.getKey());
		if (value == NULL_OBJECT) {
			return null;
		}

		return (T) value;
	}

	/**
	 * 
	 */
	public void clear() {
		cache.clear();
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return cache.size();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	protected Map<String, Object> getCache() {
		return cache;
	}

	/**
	 * 
	 * @param key
	 * @param o
	 */
	public void put(String key, Object o) {
		if (key != null) {
			cache.put(key, o);
		}
	}

	/**
	 * 
	 * @param key
	 */
	public void remove(String key) {
		cache.remove(key);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
}
