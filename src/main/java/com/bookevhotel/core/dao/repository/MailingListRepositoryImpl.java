package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.MailingList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailingListRepositoryImpl extends AbstractBookEVHotelRepository<MailingList> implements BookEVHotelRepository<MailingList> {

	protected MailingListRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(MailingList entity) {
		return null;
	}

	@Override
	public MailingList findOne(MailingList entity) {
		return null;
	}

	@Override
	public Page<MailingList> findAll(MailingList entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<MailingList> findAll(List<MailingList> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(MailingList entity) {
		return false;
	}
}
