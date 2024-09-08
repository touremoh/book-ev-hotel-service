package com.bookevhotel.core.dao;

import java.util.List;

public interface BookEVHotelRepository<E extends BookEVHotelEntity> {

	/**
	 * Find one element by criteria
	 * @param entity criteria of the element to find
	 * @return the found element
	 */
	E findOne(E entity);

	/**
	 * Find multiple elements by criteria
	 * @param entity criteria of the elements to find
	 * @return list of the found elements
	 */
	List<E> findAll(E entity);

	/**
	 * Find a list of elements using another list of elements as criteria
	 * @param entities elements criteria
	 * @return a list a elements
	 */
	List<E> findAll(List<E> entities);

	/**
	 * Create one element by criteria
	 * @param entity criteria of the elements to create
	 * @return the created element
	 */
	E createOne(E entity);

	/**
	 * Update one element by criteria
	 * @param entity criteria of the elements to create
	 * @return the created element
	 */
	E updateOne(E entity);

	/**
	 * Delete one element by criteria
	 * @param entity criteria of the element to delete
	 * @return the created element
	 */
	E deleteOne(E entity);
}
