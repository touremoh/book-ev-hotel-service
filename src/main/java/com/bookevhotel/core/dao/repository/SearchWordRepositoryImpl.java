package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SearchWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchWordRepositoryImpl extends AbstractBookEVHotelRepository<SearchWord> implements BookEVHotelRepository<SearchWord> {

	protected SearchWordRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildOneElementQuery(SearchWord entity) {
		return null;
	}

	protected Query buildFindAllQueryFromList(List<SearchWord> dictionaries, Pageable pageable) throws UnsupportedOperationException {
		List<String> dictionaryWords = new ArrayList<>();

		for (SearchWord searchWord : dictionaries) {
			dictionaryWords.add(searchWord.getKey());
		}

		return new Query(Criteria.where("key").in(dictionaryWords)).with(pageable);
	}

	@Override
	public SearchWord findOne(SearchWord entity) {
		return null;
	}

	@Override
	public Page<SearchWord> findAll(SearchWord entity, Pageable pageable) {
		return null;
	}

	@Override
	public Page<SearchWord> findAll(List<SearchWord> entities, Pageable pageable) {
		return null;
	}

	@Override
	public boolean exists(SearchWord entity) {
		return false;
	}
}
