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
	public D findOne(D dto) throws BookEVHotelException {
		// Pre-process
		this.processBeforeFindOne(dto);

		// Process
		E doc = this.findOneProcess(dto);

		// Post-process
		this.processAfterFindOne(doc);

		// Result
		return this.mapper.map(doc);
	}

	protected void processBeforeFindOne(D dto) throws BookEVHotelException {
		log.info("Before find one document: {}", 1);
	}
	protected E findOneProcess(D dto) throws BookEVHotelException {
		log.info("Find one document: Nb docs = {}", 1);
		return this.repository.findOne(this.mapper.map(dto));
	}
	protected void processAfterFindOne(E document) throws BookEVHotelException {
		log.info("After find one document: {}", 1);
	}

	/**
	 * Find multiple elements by criteria
	 *
	 * @param dto dto of the elements to find
	 * @return list of the found elements
	 */
	@Override
	public List<D> findAll(D dto) throws BookEVHotelException {
		return null;
	}

	/**
	 * Create one element by criteria
	 *
	 * @param dto criteria of the elements to create
	 * @return the created element
	 */
	@Override
	public D createOne(D dto) throws BookEVHotelException {
		return null;
	}

	/**
	 * Update one element by criteria
	 *
	 * @param dto criteria of the elements to create
	 * @return the created element
	 */
	@Override
	public D updateOne(D dto) throws BookEVHotelException {
		return null;
	}

	/**
	 * Delete one element by criteria
	 *
	 * @param dto criteria of the element to delete
	 * @return the created element
	 */
	@Override
	public D deleteOne(D dto) throws BookEVHotelException {
		return null;
	}
}
