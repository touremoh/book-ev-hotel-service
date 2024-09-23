package com.bookevhotel.core.dao;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.entity.PaymentsInformation;
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
public abstract class AbstractBookEVHotelRepository<E extends BookEVHotelEntity> {

	protected final MongoTemplate mongoTemplate;

	protected AbstractBookEVHotelRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected Criteria prepareInitialStatement(E entity) {
		if (Objects.nonNull(entity.getId())) {
			return Criteria.where("_id").is(entity.getId());
		}
		return null;
	}

	protected abstract Query buildOneElementQuery(E entity);

	protected E findOne(Query query, Class<E> entityClass) {
		return this.mongoTemplate.findOne(query, entityClass);
	}

	public Page<E> findAll(Query query, Class<E> entityClass, Pageable pageable) {
		return PageableExecutionUtils.getPage(this.mongoTemplate.find(query, entityClass), pageable, () -> this.mongoTemplate.count(query, entityClass));
	}

	public E createOne(E entity) {
		return this.mongoTemplate.insert(entity);
	}

	public E updateOne(E entity) {
		return this.mongoTemplate.save(entity);
	}

	public Boolean deleteOne(E entity) {
		return null;
	}

	public boolean exists(Query query, Class<E> entityClass) {
		return this.mongoTemplate.exists(query, entityClass);
	}
}
