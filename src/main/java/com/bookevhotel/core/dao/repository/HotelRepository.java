package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Hotel;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class HotelRepository extends AbstractBookEVHotelRepository<Hotel> {
	protected HotelRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(Hotel entity) {
		var criteria = this.prepareInitialStatement(entity);

		if (Strings.isNotEmpty(entity.getHotelName())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where("hotelName").is(entity.getHotelName());
			} else {
				criteria = criteria.and("hotelName").is(entity.getHotelName());
			}
		}
		if (Strings.isNotEmpty(entity.getWebsiteLink())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where("websiteLink").is(entity.getWebsiteLink());
			} else {
				criteria = criteria.and("websiteLink").is(entity.getWebsiteLink());
			}
		}
		return new Query(criteria);
	}

	@Override
	protected Class<Hotel> entityClass() {
		return Hotel.class;
	}
}
