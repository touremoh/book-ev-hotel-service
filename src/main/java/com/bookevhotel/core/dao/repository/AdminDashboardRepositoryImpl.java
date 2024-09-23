package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.AdminDashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDashboardRepositoryImpl extends AbstractBookEVHotelRepository<AdminDashboard> implements BookEVHotelRepository<AdminDashboard> {

	protected AdminDashboardRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(AdminDashboard entity) {
		return null;
	}

	@Override
	public AdminDashboard findOne(AdminDashboard entity) {
		return null;
	}

	@Override
	public Page<AdminDashboard> findAll(AdminDashboard entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<AdminDashboard> findAll(List<AdminDashboard> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(AdminDashboard entity) {
		return false;
	}
}
