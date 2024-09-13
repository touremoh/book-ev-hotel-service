package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.BookEVHotelMapper;
import com.bookevhotel.core.validation.BookEVHotelServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
public abstract class AbstractBookEVHotelService<E extends BookEVHotelEntity, D extends BookEVHotelDTO> implements BookEVHotelService<D> {

	protected final BookEVHotelRepository<E> repository;
	protected final BookEVHotelMapper<E, D> mapper;
	protected final BookEVHotelServiceValidator<D> validator;

	protected AbstractBookEVHotelService(BookEVHotelRepository<E> repository, BookEVHotelMapper<E, D> mapper, BookEVHotelServiceValidator<D> validator) {
		this.repository = repository;
		this.mapper = mapper;
		this.validator = validator;
	}

	@Override
	public D findOne(D dto) throws BookEVHotelException {
		// Validate
		this.validator.validateBeforeFindOne(dto);

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
		log.info("Process Before Find One");
	}
	protected E findOneProcess(D dto) throws BookEVHotelException {
		log.info("Find One Process");
		return this.repository.findOne(this.mapper.map(dto));
	}
	protected void processAfterFindOne(E entity) throws BookEVHotelException {
		log.info("Process After Find One");
	}

	/**
	 * Find multiple elements by criteria
	 *
	 * @param dto dto of the elements to find
	 * @return list of the found elements
	 */
	@Override
	public Page<D> findAll(D dto, Pageable pageable) throws BookEVHotelException {
		// Validate
		this.validator.validateBeforeFindAll(dto);

		// Pre-process
		this.processBeforeFindAll(dto);

		// Process
		Page<E> docs = this.findAllProcess(dto, pageable);

		// Post-process
		this.processAfterFindAll(docs);

		// Results
		return this.mapEntitiesToDTOs(docs);
	}
	protected void processBeforeFindAll(D dto) throws BookEVHotelException {
		log.info("Process Before Find All");
	}
	protected Page<E> findAllProcess(D dto, Pageable pageable) throws BookEVHotelException {
		log.info("Find All Process");
		return this.repository.findAll(this.mapper.map(dto), pageable);
	}
	protected void processAfterFindAll(Page<E> entity) throws BookEVHotelException {
		log.info("Process After Find All");
	}
	protected Page<D> mapEntitiesToDTOs(Page<E> page) {
		return new PageImpl<>(
			page.getContent().stream().map(this.mapper::map).toList(),
			page.getPageable(),
			page.getTotalElements()
		);
	}

	@Override
	public Page<D> findAll(List<D> dtos, Pageable pageable) throws BookEVHotelException {
		// Validate
		this.validator.validateBeforeFindAll(dtos);

		// Pre-process
		for (D dto : dtos) {
			this.processBeforeFindAll(dto);
		}

		// Process
		Page<E> docs = this.repository.findAll(dtos.stream().map(this.mapper::map).toList(), pageable);

		// Post-process
		this.processAfterFindAll(docs);

		// Results
		return mapEntitiesToDTOs(docs);
	}

	/**
	 * Create one element by criteria
	 *
	 * @param dto criteria of the elements to create
	 * @return the created element
	 */
	@Override
	public D createOne(D dto) throws BookEVHotelException {
		// Validate
		// Pre-process
		// Process
		// Post-process
		// Results
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
		// Validate
		// Pre-process
		// Process
		// Post-process
		// Results
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
		// Pre-process
		// Process
		// Post-process
		// Results
		return null;
	}
}
