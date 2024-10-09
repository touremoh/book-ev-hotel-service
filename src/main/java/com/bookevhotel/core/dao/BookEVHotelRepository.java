package com.bookevhotel.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookEVHotelRepository<E extends BookEVHotelEntity> {

	/**
	 * Find one element by criteria
	 * @param entity criteria of the element to find
	 * @return the found element
	 */
	default E findOne(E entity) {
		throw new UnsupportedOperationException("findOne is not supported");
	}

	/**
	 * Find multiple elements by criteria
	 * @param entity criteria of the elements to find
	 * @return list of the found elements
	 */
	default Page<E> findAll(E entity, Pageable pageable) {
		throw new UnsupportedOperationException("findAll is not supported");
	}

	/**
	 * Find a list of elements using another list of elements as criteria
	 * @param entities elements criteria
	 * @return a list a elements
	 */
	default Page<E> findAll(List<E> entities, Pageable pageable) {
		throw new UnsupportedOperationException("findAll is not supported");
	}

	/**
	 * Create one element by criteria
	 * @param entity criteria of the elements to create
	 * @return the created element
	 */
	default E createOne(E entity) {
		throw new UnsupportedOperationException("createOne is not supported");
	}

	/**
	 * Create many
	 * @param entities create many
	 * @return the list of created elements
	 */
	default List<E> createMany(List<E> entities) {
		throw new UnsupportedOperationException("createMany is not supported");
	}

	/**
	 * Update one element by criteria
	 * @param entity criteria of the elements to create
	 * @return the created element
	 */
	default E updateOne(E entity) {
		throw new UnsupportedOperationException("updateOne is not supported");
	}

	/**
	 * Delete one element by criteria
	 * @param entity criteria of the element to delete
	 * @return the created element
	 */
	default Boolean deleteOne(E entity) {
		throw new UnsupportedOperationException("deleteOne is not supported");
	}

	/**
	 * Check if the document exists in the db
	 * @param entity document to check
	 * @return true or false
	 */
	default boolean exists(E entity) {
		throw new UnsupportedOperationException("exists is not supported");
	}
}
