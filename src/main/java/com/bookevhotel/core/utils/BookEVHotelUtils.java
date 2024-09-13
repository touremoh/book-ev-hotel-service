package com.bookevhotel.core.utils;

import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@UtilityClass
public class BookEVHotelUtils {
	public void assertNotNull(Object object, String message) throws BookEVHotelException {
		if (Objects.isNull(object)) {
			throw new BookEVHotelException(message);
		}
	}

	public boolean allNull(Object obj) {
		return Arrays.stream(obj.getClass()
				.getDeclaredFields())
				.peek(f -> f.setAccessible(true))
				.map(f -> getFieldValue(f, obj))
				.allMatch(Objects::isNull);
	}

	private Object getFieldValue(Field field, Object target) {
		try {
			return field.get(target);
		} catch (IllegalAccessException e) {
			log.debug("Cannot check this field", e);
			return null;
		}
	}

}
