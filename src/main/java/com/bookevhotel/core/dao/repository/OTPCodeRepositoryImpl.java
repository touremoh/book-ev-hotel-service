package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.OTPCode;
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
public class OTPCodeRepositoryImpl extends AbstractBookEVHotelRepository<OTPCode> implements BookEVHotelRepository<OTPCode> {

	protected static final String FIELD_ACTIVATION_CODE = "code";
	protected static final String FIELD_USER_ID = "userId";


	protected OTPCodeRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(OTPCode otpCode) {
		var criteria = this.prepareInitialStatement(otpCode);

		if (Strings.isNotEmpty(otpCode.getCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_ACTIVATION_CODE).is(otpCode.getCode());
			} else {
				criteria = criteria.and(FIELD_ACTIVATION_CODE).is(otpCode.getCode());
			}
		}

		if (Objects.nonNull(otpCode.getUserId())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_USER_ID).is(otpCode.getUserId());
			} else {
				criteria = criteria.and(FIELD_USER_ID).is(otpCode.getUserId());
			}
		}
		return new Query(criteria);
	}

	@Override
	public OTPCode findOne(OTPCode entity) {
		return this.findOne(this.buildOneElementQuery(entity), OTPCode.class);
	}

	@Override
	public Page<OTPCode> findAll(OTPCode entity, Pageable pageable) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Page<OTPCode> findAll(List<OTPCode> entities, Pageable pageable) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public boolean exists(OTPCode entity) {
		return this.exists(this.buildOneElementQuery(entity), OTPCode.class);
	}
}
