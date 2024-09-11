package com.bookevhotel.core.utils;

import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class BookEVHotelUtils {
	public void assertNotNull(Object object, String message) throws BookEVHotelException {
		if (Objects.isNull(object)) {
			throw new BookEVHotelException(message);
		}
	}
}
