package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SearchKeyword;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class SearchKeywordRepositoryImpl extends AbstractBookEVHotelRepository<SearchKeyword> implements BookEVHotelRepository<SearchKeyword> {

	protected static final String FIELD_KEY = "key";
	protected static final String FIELD_LANGUAGE_CODE = "languageCode";


	@Autowired
	protected SearchKeywordRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(SearchKeyword searchKeyword) {
		// Prepare initial statement
		var criteria = this.prepareInitialStatement(searchKeyword);

		// Build query criteria
		if (Strings.isNotEmpty(searchKeyword.getKey())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_KEY).is(searchKeyword.getKey());
			} else {
				criteria = criteria.and(FIELD_KEY).is(searchKeyword.getKey());
			}
		}

		if (Strings.isNotEmpty(searchKeyword.getLanguageCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_LANGUAGE_CODE).is(searchKeyword.getLanguageCode());
			} else {
				criteria = criteria.and(FIELD_LANGUAGE_CODE).is(searchKeyword.getLanguageCode());
			}
		}

		// Build query
		return new Query(criteria);
	}

	@Override
	public SearchKeyword findOne(SearchKeyword searchKeyword) {
		return this.findOne(this.buildOneElementQuery(searchKeyword), SearchKeyword.class);
	}

	@Override
	public Page<SearchKeyword> findAll(SearchKeyword searchKeyword, Pageable pageable) {
		// Prepare initial statement
		var criteria = this.prepareInitialStatement(searchKeyword);

		// Build query criteria
		if (Strings.isNotEmpty(searchKeyword.getLanguageCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_LANGUAGE_CODE).is(searchKeyword.getLanguageCode());
			} else {
				criteria = criteria.and(FIELD_LANGUAGE_CODE).is(searchKeyword.getLanguageCode());
			}
		}

		// Build query
		Query query = null;
		if (Objects.isNull(criteria)) {
			query = new Query().with(pageable);
		} else {
			query = new Query(criteria).with(pageable);
		}

		// Execute
		return this.findAll(query, SearchKeyword.class, pageable);
	}

	@Override
	public Page<SearchKeyword> findAll(List<SearchKeyword> entities, Pageable pageable) {
		// Initialization
		List<String> words = new ArrayList<>();

		// Create list of words to be search
		for (SearchKeyword searchKeyword : entities) {
			words.add(searchKeyword.getKey());
		}

		// Prepare query statement
		var query = new Query(Criteria.where(FIELD_KEY).in(words)).with(pageable);

		// Execute query
		return this.findAll(query, SearchKeyword.class, pageable);
	}

	@Override
	public List<SearchKeyword> createMany(List<SearchKeyword> entities) {
		List<SearchKeyword> words = new ArrayList<>();

		for (SearchKeyword searchKeyword : entities) {
			Query query = new Query();
			query.addCriteria(Criteria
				 .where(FIELD_KEY).is(searchKeyword.getKey())
				 .and(FIELD_LANGUAGE_CODE).is(searchKeyword.getLanguageCode()));

			// Use $addToSet to add new values without duplicates
			Update update = new Update();
			update.addToSet("values").each(searchKeyword.getValues());
			var res = this.upsertOne(query, update, SearchKeyword.class);

			if (res.wasAcknowledged()) {
				log.debug("Search word {} created", searchKeyword.getKey());
				words.add(this.findOne(searchKeyword));
			} else {
				log.warn("Could not update search word: {}", searchKeyword);
				words.add(searchKeyword);
			}
		}
		return words;
	}

	@Override
	public boolean exists(SearchKeyword searchKeyword) {
		return this.exists(this.buildOneElementQuery(searchKeyword), SearchKeyword.class);
	}
}
