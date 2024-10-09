package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.ExcludedSearchKeyword;
import com.bookevhotel.core.dao.repository.ExcludedSearchKeywordRepositoryImpl;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.ExcludedSearchKeywordMapper;
import com.bookevhotel.core.validation.ExcludedSearchKeywordServiceValidator;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class ExcludedSearchKeywordService extends AbstractBookEVHotelService<ExcludedSearchKeyword, ExcludedSearchWordDTO> {
	protected ExcludedSearchKeywordService(
		ExcludedSearchKeywordRepositoryImpl repository,
		ExcludedSearchKeywordMapper mapper,
		ExcludedSearchKeywordServiceValidator validator) {
		super(repository, mapper, validator);
	}

	@Override
	protected void processBeforeFindAll(ExcludedSearchWordDTO dto) throws BookEVHotelException {
		this.lowerCaseLanguageCode(dto);
	}

	protected void lowerCaseLanguageCode(ExcludedSearchWordDTO word) {
		if (Strings.isNotEmpty(word.getLanguageCode())) {
			word.setLanguageCode(word.getLanguageCode().toLowerCase());
		}
	}
}
