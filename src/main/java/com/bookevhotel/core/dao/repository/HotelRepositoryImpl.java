package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Hotel;
import org.bson.types.ObjectId;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class HotelRepositoryImpl extends AbstractBookEVHotelRepository<Hotel> implements BookEVHotelRepository<Hotel> {

	protected static final String FIELD_HOTEL_NAME = "hotelName";
	protected static final String FIELD_WEBSITE_LINK = "websiteLink";
	protected static final String FIELD_HOTEL_DESCRIPTION = "hotelDescription";


	protected HotelRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(Hotel hotel) {
		var criteria = this.prepareInitialStatement(hotel);

		if (Strings.isNotEmpty(hotel.getHotelName())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_HOTEL_NAME).is(hotel.getHotelName());
			} else {
				criteria = criteria.and(FIELD_HOTEL_NAME).is(hotel.getHotelName());
			}
		}
		if (Strings.isNotEmpty(hotel.getWebsiteLink())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_WEBSITE_LINK).is(hotel.getWebsiteLink());
			} else {
				criteria = criteria.and(FIELD_WEBSITE_LINK).is(hotel.getWebsiteLink());
			}
		}
		return new Query(criteria);
	}

	@Override
	public Hotel findOne(Hotel entity) {
		return this.findOne(this.buildOneElementQuery(entity), Hotel.class);
	}

	@Override
	public Page<Hotel> findAll(Hotel entity, Pageable pageable) {
		// Prepare Initial Statement
		var criteria = this.prepareInitialStatement(entity);

		// Setup query criteria
		if (Strings.isNotEmpty(entity.getHotelName())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_HOTEL_NAME).regex(entity.getHotelName());
			} else {
				criteria = criteria.and(FIELD_HOTEL_NAME).regex(entity.getHotelName());
			}
		}
		if (Strings.isNotEmpty(entity.getHotelDescription())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_HOTEL_DESCRIPTION).regex(entity.getHotelDescription());
			} else {
				criteria = criteria.and(FIELD_HOTEL_DESCRIPTION).regex(entity.getHotelDescription());
			}
		}

		// Build query
		Query query = null;
		if (Objects.isNull(criteria)) {
			query = new Query().with(pageable);
		} else {
			query = new Query(criteria).with(pageable);
		}

		// Execute query
		return this.findAll(query, Hotel.class, pageable);
	}

	@Override
	public Page<Hotel> findAll(List<Hotel> entities, Pageable pageable) {
		// Initialize ids array
		List<ObjectId> ids = new ArrayList<>();

		// get all ids
		for (Hotel hotel : entities) {
			ids.add(hotel.getId());
		}

		// prepare query statement
		var query = new Query(Criteria.where(FIELD_ID).in(ids)).with(pageable);

		// execute the query
		return this.findAll(query, Hotel.class, pageable);
	}

	@Override
	public boolean exists(Hotel entity) {
		return this.exists(this.buildOneElementQuery(entity), Hotel.class);
	}
}
