package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookEVHotelController<D extends BookEVHotelDTO> {

	/**
	 * Find one element by criteria
	 * @param criteria criteria of the element to find
	 * @return the found element
	 */
	ResponseEntity<D> findOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Find multiple elements by criteria
	 * @param criteria criteria of the elements to find
	 * @return list of the found elements
	 */
	ResponseEntity<List<D>> findAllDocuments(D criteria) throws BookEVHotelException;

	/**
	 * Create one element by criteria
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	ResponseEntity<D> createOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Update one element by criteria
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	ResponseEntity<D> updateOneDocument(D criteria) throws BookEVHotelException;

	/**
	 * Delete one element by criteria
	 * @param criteria criteria of the element to delete
	 * @return the created element
	 */
	ResponseEntity<D> deleteOneDocument(D criteria) throws BookEVHotelException;
}
