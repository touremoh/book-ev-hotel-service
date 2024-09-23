package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.VisitorSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitorSearchRepositoryImpl extends AbstractBookEVHotelRepository<VisitorSearch> implements BookEVHotelRepository<VisitorSearch> {

	protected VisitorSearchRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(VisitorSearch entity) {
		return null;
	}

	@Override
	public VisitorSearch findOne(VisitorSearch entity) {
		return null;
	}

	@Override
	public Page<VisitorSearch> findAll(VisitorSearch entity, Pageable pageable) {
		return null;
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
