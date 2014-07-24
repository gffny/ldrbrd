package com.gffny.ldrbrd.common.utils;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common utils on classes for reflection.
 * 
 * @author John D. Gaffney | gffny.com
 * 
 */
public final class ClassUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ClassUtils.class);

	// Constructor declaration below is strictly to prevent SONAR warning
	// regarding presence of a public
	// or default constructor in a utility class.
	private ClassUtils() {
		throw new UnsupportedOperationException(
				"Instantiation of an instance of utility class: %s"
						+ this.getClass().getName() + " is forbidden");
	}

	/**
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class.
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(final Class<T> baseClass,
			final Class<? extends T> childClass) {
		final Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class<?>) {
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class<?>) type).getGenericSuperclass();
			} else {
				final ParameterizedType parameterizedType = (ParameterizedType) type;
				final Class<?> rawType = (Class<?>) parameterizedType
						.getRawType();

				final Type[] actualTypeArguments = parameterizedType
						.getActualTypeArguments();
				final TypeVariable<?>[] typeParameters = rawType
						.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes
							.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class<?>) {
			actualTypeArguments = ((Class<?>) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type)
					.getActualTypeArguments();
		}
		final List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		if (actualTypeArguments != null) {
			for (Type baseType : actualTypeArguments) {
				while (resolvedTypes.containsKey(baseType)) {
					baseType = resolvedTypes.get(baseType);
				}
				typeArgumentsAsClasses.add(getClass(baseType));
			}
		} else {
			LOG.warn("Arguments passed from the actual object is null");
		}
		return typeArgumentsAsClasses;
	}

	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	public static Class<?> getClass(final Type type) {
		if (type instanceof Class<?>) {
			return (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			final Type componentType = ((GenericArrayType) type)
					.getGenericComponentType();
			final Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
