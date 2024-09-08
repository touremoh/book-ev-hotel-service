package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionPlanRepository extends AbstractBookEVHotelRepository<SubscriptionPlan> {
	protected SubscriptionPlanRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(SubscriptionPlan entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<SubscriptionPlan> entityClass() {
		return SubscriptionPlan.class;
	}
}
