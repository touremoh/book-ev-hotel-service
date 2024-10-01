package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.AccountActivationCode;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class AccountActivationCodeRepositoryImpl extends AbstractBookEVHotelRepository<AccountActivationCode> implements BookEVHotelRepository<AccountActivationCode> {

	protected static final String FIELD_ACTIVATION_CODE = "activationCode";
	protected static final String FIELD_USER_ID = "userId";


	protected AccountActivationCodeRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(AccountActivationCode activationCode) {
		var criteria = this.prepareInitialStatement(activationCode);

		if (Strings.isNotEmpty(activationCode.getActivationCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_ACTIVATION_CODE).is(activationCode.getActivationCode());
			} else {
				criteria = criteria.and(FIELD_ACTIVATION_CODE).is(activationCode.getActivationCode());
			}
		}

		if (Objects.nonNull(activationCode.getUserId())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_USER_ID).is(activationCode.getUserId());
			} else {
				criteria = criteria.and(FIELD_USER_ID).is(activationCode.getUserId());
			}
		}
		return new Query(criteria);
	}

	@Override
	public AccountActivationCode findOne(AccountActivationCode entity) {
		return this.findOne(this.buildOneElementQuery(entity), AccountActivationCode.class);
	}

	@Override
	public Page<AccountActivationCode> findAll(AccountActivationCode entity, Pageable pageable) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Page<AccountActivationCode> findAll(List<AccountActivationCode> entities, Pageable pageable) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public boolean exists(AccountActivationCode entity) {
		return this.exists(this.buildOneElementQuery(entity), AccountActivationCode.class);
	}
}
