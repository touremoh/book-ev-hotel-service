package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.HotelLead;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HotelLeadRepositoryImpl extends AbstractBookEVHotelRepository<HotelLead> {

	protected HotelLeadRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildFindOneQuery(HotelLead entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<HotelLead> entityClass() {
		return HotelLead.class;
	}
}
