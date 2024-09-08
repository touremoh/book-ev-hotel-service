package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

import java.util.List;

public interface BookEVHotelService<D extends BookEVHotelDTO> {

	/**
	 * Find one element by criteria
	 * @param criteria criteria of the element to find
	 * @return the found element
	 */
	D findOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Find multiple elements by criteria
	 * @param criteria criteria of the elements to find
	 * @return list of the found elements
	 */
	List<D> findAllDocuments(D criteria) throws BookEVHotelException;

	/**
	 * Create one element by criteria
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	D createOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Update one element by criteria
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	D updateOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Delete one element by criteria
	 * @param criteria criteria of the element to delete
	 * @return the created element
	 */
	D deleteOneDocument(D criteria) throws BookEVHotelException;
}
