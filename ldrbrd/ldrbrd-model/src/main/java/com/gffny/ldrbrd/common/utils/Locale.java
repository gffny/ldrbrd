package com.gffny.ldrbrd.common.utils;

/**
 * 
 * @author John Gaffney (john@gffny.com) Feb 11, 2013
 * 
 */
public enum Locale {
	en_US("en_US", "English", "In English", "en-us"), es_ES("es_ES",
			"Espa&#x00F1;ol", "En Espa&#x00F1;ol", "es-es");

	private String locale_symbol;
	private String locale_name;
	private String locale_link;
	private String locale_idolFormat;

	/**
	 * 
	 * @param locale_symbol
	 * @param locale_name
	 * @param locale_link
	 * @param locale_idolFormat
	 */
	Locale(String locale_symbol, String locale_name, String locale_link,
			String locale_idolFormat) {
		this.locale_symbol = locale_symbol;
		this.locale_name = locale_name;
		this.locale_link = locale_link;
		this.locale_idolFormat = locale_idolFormat;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocale_symbol() {
		return locale_symbol;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocale_name() {
		return locale_name;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocale_link() {
		return locale_link;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocale_idolFormat() {
		return locale_idolFormat;
	}

	/**
	 * 
	 * @param symbol
	 * @return
	 */
	public static Locale getLocale(String symbol) {
		if (StringUtils.isEmpty(symbol))
			return null;
		for (Locale locale : Locale.values()) {
			if (StringUtils.isEquivalent(locale.getLocale_symbol(), symbol)) {
				return locale;
			}
		}
		return null;
	}

	/**
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name();
	}

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public static java.util.Locale getStandardLocale(Locale locale) {
		if (locale == Locale.en_US) {
			return new java.util.Locale("en", "US");
		} else if (locale == Locale.es_ES) {
			return new java.util.Locale("es", "ES");
		}
		return new java.util.Locale("en", "US");
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDefault() {
		return this == Locale.en_US;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNotDefault() {
		return !isDefault();
	}

	/**
	 * 
	 * @param cmsFormattedLocale
	 * @return
	 */
	public static Locale getLocaleByCMSFormat(String cmsFormattedLocale) {
		if (StringUtils.isEmpty(cmsFormattedLocale))
			return null;
		for (Locale locale : Locale.values()) {
			if (StringUtils.isEquivalent(locale.getLocale_idolFormat(),
					cmsFormattedLocale)) {
				return locale;
			}
		}
		return null;
	}
}
