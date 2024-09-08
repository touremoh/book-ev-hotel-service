package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Dictionary;
import com.bookevhotel.core.dao.repository.DictionaryRepository;
import com.bookevhotel.core.dto.DictionaryDTO;
import com.bookevhotel.core.mapper.DictionaryMapper;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService extends AbstractBookEVHotelService<Dictionary, DictionaryDTO> {
	public DictionaryService(DictionaryRepository repository, DictionaryMapper mapper) {
		super(repository, mapper);
	}
}
