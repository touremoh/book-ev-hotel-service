package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.VisitorSearch;
import org.bson.types.ObjectId;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class VisitorSearchRepositoryImpl extends AbstractBookEVHotelRepository<VisitorSearch> implements BookEVHotelRepository<VisitorSearch> {

	protected static final String FIELD_SEARCH_ID = "_id";
	protected static final String FIELD_SEARCH_TERM = "searchTerm";
	protected static final String FIELD_USER_LOCATION = "userLocation";
	protected static final String FIELD_REQUEST_TIMESTAMP = "requestTimestamp";


	protected VisitorSearchRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(VisitorSearch entity) {
		var criteria = this.prepareInitialStatement(entity);

		if (Objects.nonNull(entity.getId())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_SEARCH_ID).is(entity.getId());
			} else {
				criteria = criteria.and(FIELD_SEARCH_ID).is(entity.getId());
			}
		}

		if (Strings.isNotEmpty(entity.getSearchTerm())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_SEARCH_TERM).is(entity.getSearchTerm());
			} else {
				criteria = criteria.and(FIELD_SEARCH_TERM).is(entity.getSearchTerm());
			}
		}
		return new Query(criteria);
	}

	@Override
	public VisitorSearch findOne(VisitorSearch entity) {
		return this.findOne(this.buildOneElementQuery(entity), VisitorSearch.class);
	}

	@Override
	public Page<VisitorSearch> findAll(VisitorSearch entity, Pageable pageable) {
		// Prepare initial statement
		var criteria = this.prepareInitialStatement(entity);

		// Build query criteria
		if (Strings.isNotEmpty(entity.getSearchTerm())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_SEARCH_TERM).regex(entity.getSearchTerm());
			} else {
				criteria = criteria.and(FIELD_SEARCH_TERM).regex(entity.getSearchTerm());
			}
		}

		// Build query
		Query query = null;
		if (Objects.nonNull(criteria)) {
			query = new Query(criteria).with(pageable);
		} else {
			query = new Query().with(pageable);
		}

		// Execute query
		return this.findAll(query, VisitorSearch.class, pageable);
	}

	@Override
	public Page<VisitorSearch> findAll(List<VisitorSearch> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(VisitorSearch entity) {
		return false;
	}
}
