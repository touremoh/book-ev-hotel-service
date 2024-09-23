package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionPlanRepositoryImpl extends AbstractBookEVHotelRepository<SubscriptionPlan> implements BookEVHotelRepository<SubscriptionPlan> {

	protected SubscriptionPlanRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(SubscriptionPlan entity) {
		return null;
	}

	@Override
	public SubscriptionPlan findOne(SubscriptionPlan entity) {
		return null;
	}

	@Override
	public Page<SubscriptionPlan> findAll(SubscriptionPlan entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<SubscriptionPlan> findAll(List<SubscriptionPlan> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(SubscriptionPlan entity) {
		return false;
	}
}
