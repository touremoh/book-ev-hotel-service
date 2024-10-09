package com.bookevhotel.core.dao;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractBookEVHotelRepository<E extends BookEVHotelEntity> {

	protected static final String FIELD_ID = "_id";

	protected final MongoTemplate mongoTemplate;

	protected AbstractBookEVHotelRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected Criteria prepareInitialStatement(E entity) {
		if (Objects.nonNull(entity.getId())) {
			return Criteria.where(FIELD_ID).is(entity.getId());
		}
		return null;
	}

	protected Query buildOneElementQuery(E entity) {
		throw new UnsupportedOperationException("Not supported yet - Please implement before calling buildOneElementQuery");
	}

	protected E findOne(Query query, Class<E> entityClass) {
		return this.mongoTemplate.findOne(query, entityClass);
	}

	public Page<E> findAll(Query query, Class<E> entityClass, Pageable pageable) {
		return PageableExecutionUtils.getPage(this.mongoTemplate.find(query, entityClass), pageable, () -> this.mongoTemplate.count(query, entityClass));
	}

	public E createOne(E entity) {
		return this.mongoTemplate.insert(entity);
	}

	public UpdateResult upsertOne(Query query, Update update, Class<E> entityClass) {
		return this.mongoTemplate.upsert(query, update, entityClass);
	}

	public E updateOne(E entity) {
		return this.mongoTemplate.save(entity);
	}

	public Boolean deleteOne(E entity) {
		return this.mongoTemplate.remove(entity).getDeletedCount() == 1;
	}

	public boolean exists(Query query, Class<E> entityClass) {
		return this.mongoTemplate.exists(query, entityClass);
	}
}
