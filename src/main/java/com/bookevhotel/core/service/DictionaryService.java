package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Dictionary;
import com.bookevhotel.core.dao.repository.DictionaryRepository;
import com.bookevhotel.core.dto.DictionaryDTO;
import com.bookevhotel.core.mapper.lombok.DictionaryMapper;
import com.bookevhotel.core.validation.DictionaryServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService extends AbstractBookEVHotelService<Dictionary, DictionaryDTO> {
	@Autowired
	public DictionaryService(DictionaryRepository repository, DictionaryMapper mapper, DictionaryServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
