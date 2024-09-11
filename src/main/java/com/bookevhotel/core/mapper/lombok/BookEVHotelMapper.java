package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dto.BookEVHotelDTO;
import org.bson.types.ObjectId;

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
}
