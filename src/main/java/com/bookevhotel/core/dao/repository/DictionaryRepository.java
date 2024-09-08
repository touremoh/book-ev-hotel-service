package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Dictionary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DictionaryRepository extends AbstractBookEVHotelRepository<Dictionary> {
	protected DictionaryRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(Dictionary entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<Dictionary> entityClass() {
		return Dictionary.class;
	}
}
