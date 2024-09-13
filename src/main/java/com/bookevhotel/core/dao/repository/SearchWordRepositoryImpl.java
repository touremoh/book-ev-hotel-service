package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.AbstractBookEVHotelRepository;
import com.bookevhotel.core.dao.entity.SearchWord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchWordRepositoryImpl extends AbstractBookEVHotelRepository<SearchWord> {
	protected SearchWordRepositoryImpl(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	protected Query buildFindOneQuery(SearchWord entity) {
		return new Query(this.prepareInitialStatement(entity));
	}

	@Override
	protected Class<SearchWord> entityClass() {
		return SearchWord.class;
	}

	@Override
	protected Query buildFindAllQueryFromList(List<SearchWord> dictionaries, Pageable pageable) throws UnsupportedOperationException {
		List<String> dictionaryWords = new ArrayList<>();

		for (SearchWord searchWord : dictionaries) {
			dictionaryWords.add(searchWord.getKey());
		}

		return new Query(Criteria.where("key").in(dictionaryWords)).with(pageable);
	}
}
