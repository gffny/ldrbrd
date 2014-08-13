package com.gffny.ldrbrd.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.collections.CollectionUtils;

public class ObjectUtils extends org.apache.commons.lang.ObjectUtils {

	/**
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	static public Map<String, Object> describe(Object bean) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (bean == null)
			return null;

		Map<String, Object> map = (Map<String, Object>) BeanUtils.describe(bean);

		map = new LinkedHashMap<String, Object>(map);

		for (Field field : bean.getClass().getFields()) {
			map.put(field.getName(), field.get(bean));
		}

		return Collections.unmodifiableMap(map);
	}

	/**
	 * @param collection
	 * @param fetcher
	 * @return
	 */
	static public <T, I> Map<I, T> collectionToMap(Collection<T> collection,
			PropertyFetcher<I, ? super T> fetcher) {
		return collectionToMap(collection, fetcher, false);
	}

	/**
	 * @param collection
	 * @param fetcher
	 * @param skipOnError
	 * @return
	 */
	static public <T, I> Map<I, T> collectionToMap(Collection<T> collection,
			PropertyFetcher<I, ? super T> fetcher, boolean skipOnError) {
		Map<I, T> map = new HashMap<I, T>();

		if (!CollectionUtils.isEmpty(collection)) {
			for (T item : collection) {
				if (skipOnError) {
					try {
						map.put(fetcher.fetch(item), item);
					} catch (Throwable ex) {
						// skip
					}
				} else {
					map.put(fetcher.fetch(item), item);
				}
			}
		}

		return map;
	}

	/**
	 * @param items
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static public <T> T coalesce(T... items) {
		if (items.length > 0) {
			for (T item : items) {
				if (item != null) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * @param lhs
	 * @param rhs
	 * @param comparators
	 * @return
	 */
	static public <T> int compare(T lhs, T rhs, Collection<Comparator<T>> comparators) {
		if (!CollectionUtils.isEmpty(comparators)) {
			for (Comparator<T> comparator : comparators) {
				int result = comparator.compare(lhs, rhs);

				if (result != 0) {
					return result;
				}
			}
		}

		return 0;
	}

	/**
	 * @author John D. Gaffney | gffny.com
	 * @param <I>
	 * @param <O>
	 */
	static abstract public interface PropertyFetcher<I, O> {
		I fetch(O obj);
	}

	/**
	 * 
	 */
	static private URLCodec encoder = new URLCodec();

	/**
	 * @param object
	 * @return
	 * @throws Throwable
	 */
	static public String base64JsonEncode(Object object) throws Throwable {
		try {
			String json = JsonUtils.toJson(object, false);
			byte[] binaryData = json.getBytes();
			String base64 = Base64.encodeBase64String(binaryData);
			return encoder.encode(base64);
		} catch (Throwable e) {
			throw e;
		}
	}

	/**
	 * @param item
	 * @param fetcher
	 * @return
	 */
	public static <V> Map<String, V> convertToMap(V item, PropertyFetcher<List<String>, V> fetcher) {
		List<String> keys = fetcher.fetch(item);
		return convertToMap(keys, item);
	}

	/**
	 * @param keys
	 * @param item
	 * @return
	 */
	public static <K, V> Map<K, V> convertToMap(List<K> keys, V item) {
		Map<K, V> map = new HashMap<K, V>();

		try {
			if (CollectionUtils.isNotEmpty(keys)) {
				for (K key : keys) {
					map.put(key, item);
				}
			}

		} catch (Throwable ex) {
			// skip
		}

		return map;
	}
}
