package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookEVHotelService<D extends BookEVHotelDTO> {

	/**
	 * Find one element by dto
	 * @param dto dto of the element to find
	 * @return the found element
	 */
	D findOne(D dto) throws BookEVHotelException;

	/**
	 * Find multiple elements by dto
	 * @param dto dto of the elements to find
	 * @return list of the found elements
	 */
	Page<D> findAll(D dto, Pageable pageable) throws BookEVHotelException;

	/**
	 * Find multiple elements from a list of elements
	 * @param dtos dto of the elements to find
	 * @return list of the found elements
	 */
	Page<D> findAll(List<D> dtos, Pageable pageable) throws BookEVHotelException;

	/**
	 * Create one element by dto
	 * @param dto dto of the elements to create
	 * @return the created element
	 */
	D createOne(D dto) throws BookEVHotelException;

	/**
	 * Update one element by dto
	 * @param dto dto of the elements to create
	 * @return the created element
	 */
	D updateOne(D dto) throws BookEVHotelException;

	/**
	 * Delete one element by dto
	 * @param dto dto of the element to delete
	 * @return the created element
	 */
	D deleteOne(D dto) throws BookEVHotelException;
}
