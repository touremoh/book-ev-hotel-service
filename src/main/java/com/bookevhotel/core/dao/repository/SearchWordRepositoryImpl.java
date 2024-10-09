package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SearchWord;
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
public class SearchWordRepositoryImpl extends AbstractBookEVHotelRepository<SearchWord> implements BookEVHotelRepository<SearchWord> {

	protected static final String FIELD_KEY = "key";
	protected static final String FIELD_LANGUAGE_CODE = "languageCode";


	@Autowired
	protected SearchWordRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(SearchWord searchWord) {
		// Prepare initial statement
		var criteria = this.prepareInitialStatement(searchWord);

		// Build query criteria
		if (Strings.isNotEmpty(searchWord.getKey())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_KEY).is(searchWord.getKey());
			} else {
				criteria = criteria.and(FIELD_KEY).is(searchWord.getKey());
			}
		}

		if (Strings.isNotEmpty(searchWord.getLanguageCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode());
			} else {
				criteria = criteria.and(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode());
			}
		}

		// Build query
		return new Query(criteria);
	}

	@Override
	public SearchWord findOne(SearchWord searchWord) {
		return this.findOne(this.buildOneElementQuery(searchWord), SearchWord.class);
	}

	@Override
	public Page<SearchWord> findAll(SearchWord searchWord, Pageable pageable) {
		// Prepare initial statement
		var criteria = this.prepareInitialStatement(searchWord);

		// Build query criteria
		if (Strings.isNotEmpty(searchWord.getLanguageCode())) {
			if (Objects.isNull(criteria)) {
				criteria = Criteria.where(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode());
			} else {
				criteria = criteria.and(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode());
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
		return this.findAll(query, SearchWord.class, pageable);
	}

	@Override
	public Page<SearchWord> findAll(List<SearchWord> entities, Pageable pageable) {
		// Initialization
		List<String> words = new ArrayList<>();

		// Create list of words to be search
		for (SearchWord searchWord : entities) {
			words.add(searchWord.getKey());
		}

		// Prepare query statement
		var query = new Query(Criteria.where(FIELD_KEY).in(words)).with(pageable);

		// Execute query
		return this.findAll(query, SearchWord.class, pageable);
	}

	@Override
	public List<SearchWord> createMany(List<SearchWord> entities) {
		List<SearchWord> words = new ArrayList<>();

		for (SearchWord searchWord : entities) {
			Query query = new Query();
			query.addCriteria(Criteria
				 .where(FIELD_KEY).is(searchWord.getKey())
				 .and(FIELD_LANGUAGE_CODE).is(searchWord.getLanguageCode()));

			// Use $addToSet to add new values without duplicates
			Update update = new Update();
			update.addToSet("values").each(searchWord.getValues());
			var res = this.upsertOne(query, update, SearchWord.class);

			if (res.wasAcknowledged()) {
				log.debug("Search word {} created", searchWord.getKey());
				words.add(this.findOne(searchWord));
			} else {
				log.warn("Could not update search word: {}", searchWord);
				words.add(searchWord);
			}
		}
		return words;
	}

	@Override
	public boolean exists(SearchWord searchWord) {
		return this.exists(this.buildOneElementQuery(searchWord), SearchWord.class);
	}
}
