package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.VisitorSearch;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class VisitorSearchRepository extends AbstractBookEVHotelRepository<VisitorSearch> {

	protected VisitorSearchRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(VisitorSearch entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<VisitorSearch> entityClass() {
		return VisitorSearch.class;
	}
}
