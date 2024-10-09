package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.SearchWord;
import com.bookevhotel.core.dao.repository.SearchWordRepositoryImpl;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.SearchWordMapper;
import com.bookevhotel.core.validation.SearchWordServiceValidator;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchWordService extends AbstractBookEVHotelService<SearchWord, SearchWordDTO> {
	@Autowired
	public SearchWordService(SearchWordRepositoryImpl repository, SearchWordMapper mapper, SearchWordServiceValidator validator) {
		super(repository, mapper, validator);
	}

	@Override
	protected void processBeforeCreateOne(SearchWordDTO dto) throws BookEVHotelException {
		this.lowerCaseLanguageCode(dto);
	}

	@Override
	protected void processBeforeFindOne(SearchWordDTO dto) throws BookEVHotelException {
		this.lowerCaseLanguageCode(dto);
	}

	@Override
	protected void processBeforeFindAll(SearchWordDTO dto) throws BookEVHotelException {
		this.lowerCaseLanguageCode(dto);
	}

	@Override
	protected void processBeforeUpdateOne(SearchWordDTO dto) throws BookEVHotelException {
		this.lowerCaseLanguageCode(dto);
	}

	@Override
	protected void processBeforeCreateMany(List<SearchWordDTO> dtos) throws BookEVHotelException {
		for (SearchWordDTO dto : dtos) {
			this.lowerCaseLanguageCode(dto);
		}
	}

	protected void lowerCaseLanguageCode(SearchWordDTO word) {
		if (Strings.isNotEmpty(word.getLanguageCode())) {
			word.setLanguageCode(word.getLanguageCode().toLowerCase());
		}
	}
}
