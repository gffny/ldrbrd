package com.gffny.ldrbrd.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gffny.ldrbrd.common.utils.ObjectUtils.PropertyFetcher;

public class MapUtils extends org.apache.commons.collections.MapUtils {

	static public boolean containsKey(Map<?, ?> map, Object key) {
		if (isEmpty(map)) {
			return false;
		}

		return map.containsKey(key);
	}

	static public boolean doesNotContainKey(Map<?, ?> map, Object key) {
		return !containsKey(map, key);
	}

	public static int safeSize(Map<?, ?> map) {
		return (map != null) ? map.size() : 0;
	}

	public static String getString(Map<String, Object> rs, String key) {
		try {
			return StringUtils.valueOf(rs.get(key));
		} catch (Throwable ex) {
			return null;
		}
	}

	public static String getStringIgnoreCase(Map<String, Object> rs, String key) {
		try {
			return StringUtils.valueOf(rs.get(StringUtils.upperCase(key)));
		} catch (Throwable ex) {
			return null;
		}
	}

	public static int getInt(Map<String, Object> rs, String key) {
		return StringUtils.parseInteger(getString(rs, key), 0);
	}

	public static double getDouble(Map<String, Object> rs, String key) {
		return StringUtils.parseDouble(getString(rs, key), 0);
	}

	public static Set<String> getSet(Map<String, Object> rs, String key,
			String separator) {
		try {
			String value = getString(rs, key);
			return StringUtils.isEmpty(value) ? null : new HashSet<String>(
					Arrays.asList(StringUtils.split(value, separator)));
		} catch (Throwable ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Object> getList(Map<String, Object> rs, String key) {
		Object value = getObject(rs, key);
		if (value == null) {
			return new ArrayList<Object>();
		}

		if (value instanceof List) {
			return (List<Object>) value;
		}

		List<Object> values = new ArrayList<Object>();
		values.add(value);
		return values;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getListOfMaps(
			Map<String, Object> rs, String key) {
		Object value = getObject(rs, key);
		if (value == null) {
			return new ArrayList<Map<String, Object>>();
		}

		if (value instanceof List) {
			return (List<Map<String, Object>>) value;
		}

		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		values.add((Map<String, Object>) value);
		return values;
	}

	public static <U, V> Map<U, V> addToMap(Map<U, V> dataMap, U key, V value) {
		if (key == null && value == null) {
			return dataMap;
		}

		dataMap.put(key, value);
		return dataMap;
	}

	public static <U, V> List<Map<U, V>> addToListofMap(
			List<Map<U, V>> listofDataMap, U key, V value) {
		if (!CollectionUtils.isEmpty(listofDataMap)) {
			for (Map<U, V> map : listofDataMap) {
				addToMap(map, key, value);
			}
		}
		return listofDataMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMap(Map<String, Object> rs, String key) {
		Object value = getObject(rs, key);
		if (value == null) {
			return new HashMap<String, Object>();
		}

		if (value instanceof Map) {
			return (Map<String, Object>) value;
		}

		return new HashMap<String, Object>();
	}

	public static <E extends Enum<E>> E getEnumByOrdinal(
			Map<String, Object> rs, String key, Class<E> enumClass) {
		try {
			int ordinal = (Integer) rs.get(key);
			return enumClass.getEnumConstants()[ordinal];
		} catch (Throwable ex) {
			return null;
		}
	}

	public static <E extends Enum<E>> E getEnumByName(String name,
			Class<E> enumClass) {
		try {
			return (name == null) ? null : Enum.valueOf(enumClass, name);
		} catch (Throwable ex) {
			return null;
		}
	}

	public static <K, V> Map<K, V> filter(Map<K, V> map, List<K> keys) {
		if (map == null) {
			return null;
		}

		Map<K, V> filtered = new LinkedHashMap<K, V>();

		for (K key : keys) {
			if (map.containsKey(key)) {
				filtered.put(key, map.get(key));
			}
		}

		return filtered;
	}

	public static <K, V> Map<K, V> clone(Map<K, V> m) {
		return m == null ? null : new LinkedHashMap<K, V>(m);
	}

	public static <K, V> Map<K, V> cloneToEmpty(Map<K, V> m) {
		return m == null ? new LinkedHashMap<K, V>() : clone(m);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> getMapInto(Map<?, ?> map, Object mkey,
			Map<K, V> into) {
		Map<?, ?> generic = getMap(map, mkey);

		if (generic != null) {
			for (Entry<?, ?> entry : generic.entrySet()) {
				K key = (K) entry.getKey();
				V value = (V) entry.getValue();

				into.put(key, value);
			}
		}

		return into;
	}

	public static <IK, IV, OK, OV> Map<OK, OV> transform(
			final Map<IK, IV> source, Transformer<IK, IV, OK, OV> transformer) {
		if (source == null) {
			return null;
		}

		Map<OK, OV> output = new LinkedHashMap<OK, OV>();
		for (Entry<IK, IV> input : source.entrySet()) {
			Entry<OK, OV> o = transformer.transform(input);
			output.put(o.getKey(), o.getValue());
		}

		return output;
	}

	public static interface Transformer<IK, IV, OK, OV> {
		Entry<OK, OV> transform(Entry<IK, IV> item);
	}

	public static class EntryImpl<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		public EntryImpl(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V old = this.value;

			this.value = value;

			return old;
		}

	}

	public static <I, O1> Map<O1, I> toMap(List<I> collection,
			PropertyFetcher<O1, I> keyFetcher) {

		Map<O1, I> map = new HashMap<O1, I>();

		if (!CollectionUtils.isEmpty(collection)) {
			for (I element : collection) {
				map.put(keyFetcher.fetch(element), element);
			}
		}
		return map;
	}

	public static <I, O1, O2> Map<O1, O2> toMap(List<I> collection,
			PropertyFetcher<O1, I> keyFetcher,
			PropertyFetcher<O2, I> valueFetcher) {

		Map<O1, O2> map = new HashMap<O1, O2>();

		if (!CollectionUtils.isEmpty(collection)) {
			for (I element : collection) {
				map.put(keyFetcher.fetch(element), valueFetcher.fetch(element));
			}
		}
		return map;
	}

	public static <I, O1, O2> MultiValueMap<O1, O2> toMultiValueMap(
			List<I> collection, PropertyFetcher<O1, I> keyFetcher,
			PropertyFetcher<O2, I> valueFetcher) {
		MultiValueMap<O1, O2> map = new LinkedMultiValueMap<O1, O2>();
		if (!CollectionUtils.isEmpty(collection)) {
			for (I element : collection) {
				map.add(keyFetcher.fetch(element), valueFetcher.fetch(element));
			}
		}
		return map;
	}

	public static <U, V> MultiValueMap<U, V> addToMultiValueMap(
			MultiValueMap<U, V> dataMap, U key, V value) {
		if (key == null && value == null) {
			return dataMap;
		}

		dataMap.add(key, value);
		return dataMap;
	}
}
