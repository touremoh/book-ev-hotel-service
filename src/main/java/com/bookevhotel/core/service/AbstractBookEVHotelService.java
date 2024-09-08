package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.BookEVHotelMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AbstractBookEVHotelService<E extends BookEVHotelEntity, D extends BookEVHotelDTO> implements BookEVHotelService<D> {

	protected final BookEVHotelRepository<E> repository;
	protected final BookEVHotelMapper<E, D> mapper;

	public AbstractBookEVHotelService(BookEVHotelRepository<E> repository, BookEVHotelMapper<E, D> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public D findOneDocument(D criteria) throws BookEVHotelException {
		// Pre-process
		this.processBeforeFindOneDocument(criteria);

		// Process
		E doc = this.processFindOneDocument(criteria);

		// Post-process
		this.processAfterFindOneDocument(doc);

		// Result
		return this.mapper.map(doc);
	}

	protected void processBeforeFindOneDocument(D criteria) throws BookEVHotelException {
		log.info("Before find one document: {}", 1);
	}
	protected E processFindOneDocument(D criteria) throws BookEVHotelException {
		log.info("Find one document: Nb docs = {}", 1);
		return this.repository.findOne(this.mapper.map(criteria));
	}
	protected void processAfterFindOneDocument(E document) throws BookEVHotelException {
		log.info("After find one document: {}", 1);
	}

	/**
	 * Find multiple elements by criteria
	 *
	 * @param criteria criteria of the elements to find
	 * @return list of the found elements
	 */
	@Override
	public List<D> findAllDocuments(D criteria) throws BookEVHotelException {
		return null;
	}

	/**
	 * Create one element by criteria
	 *
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	@Override
	public D createOneDocument(D criteria) throws BookEVHotelException {
		return null;
	}

	/**
	 * Update one element by criteria
	 *
	 * @param criteria criteria of the elements to create
	 * @return the created element
	 */
	@Override
	public D updateOneDocument(D criteria) throws BookEVHotelException {
		return null;
	}

	/**
	 * Delete one element by criteria
	 *
	 * @param criteria criteria of the element to delete
	 * @return the created element
	 */
	@Override
	public D deleteOneDocument(D criteria) throws BookEVHotelException {
		return null;
	}
}
