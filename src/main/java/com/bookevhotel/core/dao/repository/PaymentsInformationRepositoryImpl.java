package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.PaymentsInformation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentsInformationRepositoryImpl extends AbstractBookEVHotelRepository<PaymentsInformation> {


	protected PaymentsInformationRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildFindOneQuery(PaymentsInformation entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<PaymentsInformation> entityClass() {
		return PaymentsInformation.class;
	}
}
