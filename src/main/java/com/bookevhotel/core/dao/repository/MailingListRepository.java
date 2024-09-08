package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.MailingList;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MailingListRepository extends AbstractBookEVHotelRepository<MailingList> {

	protected MailingListRepository(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildQuery(MailingList entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<MailingList> entityClass() {
		return MailingList.class;
	}
}
