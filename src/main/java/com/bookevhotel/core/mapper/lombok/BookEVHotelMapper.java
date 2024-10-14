package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.util.Objects;

public interface BookEVHotelMapper<E extends BookEVHotelEntity, D extends BookEVHotelDTO> {

	/**
	 * Convert an entity to DTO
	 * @param entity to convert
	 * @return DTO to return
	 */
	D map(E entity);

	/**
	 * Convert a DTO to an Entity
	 * @param dto to convert
	 * @return an entity
	 */
	E map(D dto);

	/**
	 * Convert the primary key from string type to ObjectId
	 * @param id primary key to convert
	 * @return converted objectId
	 */
	default ObjectId stringToObjectId(String id) {
		if (Objects.nonNull(id)) {
			return new ObjectId(id);
		}
		return null;
	}

	/**
	 * Convert the primary key ObjectId from object to string
	 * @param id primary key to convert
	 * @return the converted primary key in string format
	 */
	default String objectIdToString(ObjectId id) {
		return Objects.nonNull(id) ? id.toString() : null;
	}

	default void merge(D target, D source) throws BookEVHotelException {
		if (target == null || source == null) {
			throw new BookEVHotelException("Target and source objects must not be null.");
		}

		Class<?> clazz = target.getClass();

		// Iterate over all fields of the class
		for (Field field : clazz.getDeclaredFields()) {
			// Allow private fields to be accessed
			field.setAccessible(true);

			try {
				// Get value of the field in target
				Object targetValue = field.get(target);

				// Get value of the field in source
				Object sourceValue = field.get(source);

				// Update only if the target field is null and the source field is not null
				if (targetValue == null && sourceValue != null) {
					field.set(target, sourceValue); // Set target field to source field value
				}
			} catch (IllegalAccessException e) {
				throw new BookEVHotelException("Could not access field: " + field.getName(), e);
			}
		}
	}
}
