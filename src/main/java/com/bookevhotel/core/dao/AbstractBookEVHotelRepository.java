package com.bookevhotel.core.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractBookEVHotelRepository<E extends BookEVHotelEntity> implements BookEVHotelRepository<E> {

	protected final MongoTemplate mongoTemplate;

	protected AbstractBookEVHotelRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected abstract Query buildFindOneQuery(E entity);
	protected abstract Class<E> entityClass();

	protected Query buildFindAllQuery(E entity, Pageable pageable) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	protected Query buildFindAllQueryFromList(List<E> entities, Pageable pageable) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	protected Criteria prepareInitialStatement(E entity) {
		if (Objects.nonNull(entity.getId())) {
			return Criteria.where("_id").is(entity.getId());
		}
		return null;
	}

	@Override
	public E findOne(E entity) {
		return this.mongoTemplate.findOne(this.buildFindOneQuery(entity), this.entityClass());
	}

	@Override
	public Page<E> findAll(E entity, Pageable pageable) {
		var query = this.buildFindAllQuery(entity, pageable);
		return PageableExecutionUtils.getPage(this.mongoTemplate.find(query, this.entityClass()), pageable, () -> this.mongoTemplate.count(query, this.entityClass()));
	}

	@Override
	public Page<E> findAll(List<E> entities, Pageable pageable) {
		var query = this.buildFindAllQueryFromList(entities, pageable);
		return PageableExecutionUtils.getPage(this.mongoTemplate.find(query, this.entityClass()), pageable, () -> this.mongoTemplate.count(query, this.entityClass()));
	}

	@Override
	public E createOne(E entity) {
		return this.mongoTemplate.insert(entity);
	}

	@Override
	public E updateOne(E entity) {
		return this.mongoTemplate.save(entity);
	}

	@Override
	public E deleteOne(E entity) {
		return null;
	}
}
