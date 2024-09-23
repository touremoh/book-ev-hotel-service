package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.HotelUser;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class HotelUserRepositoryImpl extends AbstractBookEVHotelRepository<HotelUser> implements BookEVHotelRepository<HotelUser> {

	protected static final String FIELD_EMAIL = "email";
	protected static final String FIELD_HOTEL_ID = "hotelId";

	protected HotelUserRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(HotelUser entity) {
		Criteria criteria = this.prepareInitialStatement(entity);

		if (Strings.isNotEmpty(entity.getEmail())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_EMAIL).is(entity.getEmail());
			} else {
				criteria = criteria.and(FIELD_EMAIL).is(entity.getEmail());
			}
		}
		return new Query(criteria);
	}


	@Override
	public HotelUser findOne(HotelUser entity) {
		return  this.mongoTemplate.findOne(this.buildOneElementQuery(entity), HotelUser.class);
	}

	@Override
	public boolean exists(HotelUser entity) {
		return this.mongoTemplate.exists(this.buildOneElementQuery(entity), HotelUser.class);
	}

	@Override
	public Page<HotelUser> findAll(HotelUser entity, Pageable pageable) {
		Query query = new Query();
		if (Objects.nonNull(entity.getHotelId())) {
			query = new Query(Criteria.where(FIELD_HOTEL_ID).is(entity.getHotelId())).with(pageable);
		} else {
			query = query.with(pageable);
		}
		Query finalQuery = query;
		return PageableExecutionUtils.getPage(
			this.mongoTemplate.find(query, HotelUser.class), pageable,
			() -> this.mongoTemplate.count(finalQuery, HotelUser.class)
		);
	}

	@Override
	public Page<HotelUser> findAll(List<HotelUser> entities, Pageable pageable) {
		throw new UnsupportedOperationException("Find All users from list not supported yet");
	}
}
