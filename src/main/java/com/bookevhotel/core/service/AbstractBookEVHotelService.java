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
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

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
		D doc = this.findOneProcess(dto);

		// Post-process
		this.processAfterFindOne(doc);

		// Result
		return doc;
	}

	protected void processBeforeFindOne(D dto) throws BookEVHotelException {
		log.info("Process Before Find One");
	}
	protected D findOneProcess(D dto) throws BookEVHotelException {
		try {
			// Exception to be thrown if user not found
			final var resourceNotFound = new BookEVHotelException(
				"Unable to find resource",
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND
			);

			// Convert DTO to entity
			var user = this.mapper.map(dto);

			// Check if user was found before returning the results
			return this.mapper.map(ofNullable(this.repository.findOne(user)).orElseThrow(() -> resourceNotFound));
		} catch (Exception e) {
			throw new BookEVHotelException("Error during findOneProcess", e);
		}
	}
	protected void processAfterFindOne(D dto) throws BookEVHotelException {
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
		Page<D> docs = this.findAllProcess(dto, pageable);

		// Post-process
		this.processAfterFindAll(docs);

		// Results
		return docs;
	}
	protected void processBeforeFindAll(D dto) throws BookEVHotelException {
		log.info("Process Before Find All");
	}
	protected Page<D> findAllProcess(D dto, Pageable pageable) throws BookEVHotelException {
		try {
			return this.mapEntitiesToDTOs(this.repository.findAll(this.mapper.map(dto), pageable));
		} catch (Exception e) {
			throw new BookEVHotelException("Error during findAllProcess", e);
		}
	}
	protected void processAfterFindAll(Page<D> data) throws BookEVHotelException {
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
		Page<D> docs = this.findAllProcess(dtos, pageable);

		// Post-process
		this.processAfterFindAll(docs);

		// Results
		return docs;
	}

	protected Page<D> findAllProcess(List<D> dtos, Pageable pageable) throws BookEVHotelException {
		try {
			return this.mapEntitiesToDTOs(this.repository.findAll(dtos.stream().map(this.mapper::map).toList(), pageable));
		} catch (Exception e) {
			throw new BookEVHotelException("Error during findAllProcess", e);
		}
	}

	/**
	 * Create one element by criteria
	 *
	 * @param dto criteria of the elements to create
	 * @return the created element
	 */
	@Override
	@Transactional
	public D createOne(D dto) throws BookEVHotelException {
		// Validate
		this.validator.validateBeforeCreateOne(dto);

		// Pre-process
		this.processBeforeCreateOne(dto);

		// Process
		D doc = this.createOneProcess(dto);

		// Post-process
		this.processAfterCreateOne(doc);

		// Results
		return doc;
	}
	protected void processBeforeCreateOne(D dto) throws BookEVHotelException {
		log.debug("Process Before Create One");
	}
	protected void processAfterCreateOne(D dto) throws BookEVHotelException {
		log.debug("Process After Create One");
	}
	protected D createOneProcess(D dto) throws BookEVHotelException {
		return this.mapper.map(this.repository.createOne(this.mapper.map(dto)));
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
		this.validator.validateBeforeUpdateOne(dto);

		// Pre-process
		this.processBeforeUpdateOne(dto);

		// Process
		D doc = this.updateOneProcess(dto);

		// Post-process
		this.processAfterUpdateOne(doc);

		// Results
		return doc;
	}
	protected void processBeforeUpdateOne(D dto) throws BookEVHotelException {
		log.debug("Process Before Update One");
	}
	protected void processAfterUpdateOne(D dto) throws BookEVHotelException {
		log.debug("Process After Update One");
	}
	protected D updateOneProcess(D dto) throws BookEVHotelException {
		return this.mapper.map(this.repository.updateOne(this.mapper.map(dto)));
	}

	/**
	 * Delete one element by criteria
	 * @param dto criteria of the element to delete
	 * @return the created element
	 */
	@Override
	public Boolean deleteOne(D dto) throws BookEVHotelException {
		if (Objects.isNull(dto) || Objects.isNull(dto.getId())) {
			throw new BookEVHotelException("Invalid request");
		}
		return this.repository.deleteOne(this.mapper.map(dto));
	}
}
