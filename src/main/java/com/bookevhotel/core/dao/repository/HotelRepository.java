package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Hotel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRepository extends AbstractBookEVHotelRepository<Hotel> {
	protected HotelRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(Hotel entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<Hotel> entityClass() {
		return Hotel.class;
	}
}
