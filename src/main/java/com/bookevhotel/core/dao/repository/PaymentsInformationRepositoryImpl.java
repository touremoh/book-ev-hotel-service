package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.PaymentsInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentsInformationRepositoryImpl extends AbstractBookEVHotelRepository<PaymentsInformation> implements BookEVHotelRepository<PaymentsInformation> {


	protected PaymentsInformationRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(PaymentsInformation entity) {
		return null;
	}

	@Override
	public PaymentsInformation findOne(PaymentsInformation entity) {
		return null;
	}

	@Override
	public Page<PaymentsInformation> findAll(PaymentsInformation entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<PaymentsInformation> findAll(List<PaymentsInformation> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(PaymentsInformation entity) {
		return false;
	}
}
