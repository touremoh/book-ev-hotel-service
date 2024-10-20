package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.HotelLead;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class HotelLeadRepositoryImpl extends AbstractBookEVHotelRepository<HotelLead> implements BookEVHotelRepository<HotelLead> {

	protected static final String FIELD_EMAIL = "email";
	protected static final String FIELD_HOTEL_ID = "hotelId";


	@Autowired
	protected HotelLeadRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(HotelLead hotelLead) {
		// Init Criteria
		var criteria = this.prepareInitialStatement(hotelLead);

		// Set Lead Email
		if (Strings.isNotEmpty(hotelLead.getEmail())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_EMAIL).is(hotelLead.getEmail());
			} else {
				criteria = criteria.and(FIELD_EMAIL).is(hotelLead.getEmail());
			}
		}

		// Build query
		return new Query(criteria);
	}

	@Override
	public Page<HotelLead> findAll(HotelLead hotelLead, Pageable pageable) {
		// Prepare Initial Statement
		var criteria = Criteria.where(FIELD_HOTEL_ID).is(hotelLead.getHotelId());

		// Build query
		Query query = new Query(criteria).with(pageable);

		// Execute query
		return this.findAll(query, HotelLead.class, pageable);
	}
}
