package com.bookevhotel.core.validation;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

public interface BookEVHotelServiceValidator<D extends BookEVHotelDTO> {

	/**
	 * Validate data before finding one document
	 * @param dto Data to validate
	 * @throws BookEVHotelException thrown when validation fails
	 */
	void validateBeforeFindOne(D dto) throws BookEVHotelException;


	/**
	 * Validate find all
	 * @param dto Data to validate
	 * @throws BookEVHotelException thrown when validation fails
	 */
	void validateBeforeFindAll(D dto) throws BookEVHotelException;


	/**
	 * Validate a DTO before creating new document
	 * @param dto Object to create
	 * @throws BookEVHotelException Thrown when creation failed
	 */
	void validateBeforeCreateOne(D dto) throws BookEVHotelException;

	/**
	 * Validate data before updating an existing document
	 * @param dto Data to validate
	 * @throws BookEVHotelException Thrown when validation failed
	 */
	void validateBeforeUpdateOne(D dto) throws BookEVHotelException;
}
