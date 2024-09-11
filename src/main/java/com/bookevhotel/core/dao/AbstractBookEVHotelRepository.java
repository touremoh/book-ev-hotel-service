package com.bookevhotel.core.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractBookEVHotelRepository<E extends BookEVHotelEntity> implements BookEVHotelRepository<E> {

	protected final MongoTemplate mongoTemplate;

	protected AbstractBookEVHotelRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected abstract Query buildQuery(E entity);
	protected abstract Class<E> entityClass();

	protected Criteria prepareInitialStatement(E entity) {
		if (Objects.nonNull(entity.getId())) {
			return Criteria.where("_id").is(entity.getId());
		}
		return null;
	}

	@Override
	public E findOne(E entity) {
		return this.mongoTemplate.findOne(this.buildQuery(entity), this.entityClass());
	}

	@Override
	public List<E> findAll(E entity) {
		return this.mongoTemplate.find(this.buildQuery(entity), this.entityClass());
	}

	@Override
	public List<E> findAll(List<E> entities) {
		return List.of(); /// TODO
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
