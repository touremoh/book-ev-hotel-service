package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.SearchKeyword;
import com.bookevhotel.core.dao.repository.SearchKeywordRepositoryImpl;
import com.bookevhotel.core.dto.SearchKeywordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.SearchKeywordMapper;
import com.bookevhotel.core.validation.SearchKeywordServiceValidator;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchKeywordService extends AbstractBookEVHotelService<SearchKeyword, SearchKeywordDTO> {
	@Autowired
	public SearchKeywordService(SearchKeywordRepositoryImpl repository, SearchKeywordMapper mapper, SearchKeywordServiceValidator validator) {
		super(repository, mapper, validator);
	}

	@Override
	protected void processBeforeCreateOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		this.lowerCaseLanguageCode(searchKeywordDTO);
	}

	@Override
	protected void processBeforeFindOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		this.lowerCaseLanguageCode(searchKeywordDTO);
	}

	@Override
	protected void processBeforeFindAll(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		this.lowerCaseLanguageCode(searchKeywordDTO);
	}

	@Override
	protected void processBeforeUpdateOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		this.lowerCaseLanguageCode(searchKeywordDTO);
	}

	@Override
	protected void processBeforeCreateMany(List<SearchKeywordDTO> searchKeywordDTOs) throws BookEVHotelException {
		for (SearchKeywordDTO keywordDTO : searchKeywordDTOs) {
			this.lowerCaseLanguageCode(keywordDTO);
		}
	}

	protected void lowerCaseLanguageCode(SearchKeywordDTO searchKeywordDTO) {
		if (Strings.isNotEmpty(searchKeywordDTO.getLanguageCode())) {
			searchKeywordDTO.setLanguageCode(searchKeywordDTO.getLanguageCode().toLowerCase());
		}
	}
}
