package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Hotel;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Pageable;
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
	protected Query buildFindOneQuery(Hotel entity) {
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
	protected Query buildFindAllQuery(Hotel entity, Pageable pageable) {
		var criteria = this.prepareInitialStatement(entity);

		if (Strings.isNotEmpty(entity.getHotelName())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where("hotelName").regex(entity.getHotelName());
			} else {
				criteria = criteria.and("hotelName").regex(entity.getHotelName());
			}
		}
		if (Strings.isNotEmpty(entity.getHotelDescription())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where("hotelDescription").regex(entity.getHotelDescription());
			} else {
				criteria = criteria.and("hotelDescription").regex(entity.getHotelDescription());
			}
		}

		if (Objects.isNull(criteria)) {
			criteria = Criteria.where("1").is("1");
		}

		return new Query(criteria).with(pageable);
	}

	@Override
	protected Class<Hotel> entityClass() {
		return Hotel.class;
	}
}
