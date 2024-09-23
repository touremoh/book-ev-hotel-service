package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.HotelLead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelLeadRepositoryImpl extends AbstractBookEVHotelRepository<HotelLead> implements BookEVHotelRepository<HotelLead> {

	protected HotelLeadRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(HotelLead entity) {
		return null;
	}

	@Override
	public HotelLead findOne(HotelLead entity) {
		return null;
	}

	@Override
	public Page<HotelLead> findAll(HotelLead entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<HotelLead> findAll(List<HotelLead> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(HotelLead entity) {
		return false;
	}
}
