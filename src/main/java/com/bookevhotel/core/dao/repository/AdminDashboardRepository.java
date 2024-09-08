package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.AdminDashboard;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDashboardRepository extends AbstractBookEVHotelRepository<AdminDashboard> {
	protected AdminDashboardRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(AdminDashboard entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<AdminDashboard> entityClass() {
		return AdminDashboard.class;
	}
}
