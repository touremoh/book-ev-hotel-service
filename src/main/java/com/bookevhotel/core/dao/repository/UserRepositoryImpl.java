package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractBookEVHotelRepository<User> {

	protected UserRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildFindOneQuery(User entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<User> entityClass() {
		return User.class;
	}
}
