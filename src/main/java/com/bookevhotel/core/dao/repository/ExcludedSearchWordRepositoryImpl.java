package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.ExcludedSearchWord;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
public class ExcludedSearchWordRepositoryImpl extends AbstractBookEVHotelRepository<ExcludedSearchWord> implements BookEVHotelRepository<ExcludedSearchWord> {

	protected static final String FIELD_LANGUAGE_CODE = "languageCode";

	@Autowired
	protected ExcludedSearchWordRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	public Page<ExcludedSearchWord> findAll(ExcludedSearchWord searchWord, Pageable pageable) {
		// Prepare initial statement
		Criteria criteria = null;

		// Build query criteria
		if (Strings.isNotEmpty(searchWord.getLanguageCode())) {
			criteria = Criteria.where(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode());
		}

		// Build query
		Query query = null;
		if (Objects.isNull(criteria)) {
			query = new Query().with(pageable);
		} else {
			query = new Query(criteria).with(pageable);
		}

		// Execute
		return this.findAll(query, ExcludedSearchWord.class, pageable);
	}
}
