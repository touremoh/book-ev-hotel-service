package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.SearchWord;
import com.bookevhotel.core.dao.repository.SearchWordRepositoryImpl;
import com.bookevhotel.core.dto.SearchWordsDTO;
import com.bookevhotel.core.mapper.lombok.SearchWordMapper;
import com.bookevhotel.core.validation.DictionaryServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService extends AbstractBookEVHotelService<SearchWord, SearchWordsDTO> {
	@Autowired
	public DictionaryService(SearchWordRepositoryImpl repository, SearchWordMapper mapper, DictionaryServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
