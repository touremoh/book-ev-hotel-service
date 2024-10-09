package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.ExcludedSearchWord;
import com.bookevhotel.core.dao.repository.ExcludedSearchWordRepositoryImpl;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.ExcludedSearchWordMapper;
import com.bookevhotel.core.validation.ExcludedSearchWordServiceValidator;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class ExcludedSearchWordService extends AbstractBookEVHotelService<ExcludedSearchWord, ExcludedSearchWordDTO> {
	protected ExcludedSearchWordService(
		ExcludedSearchWordRepositoryImpl repository,
		ExcludedSearchWordMapper mapper,
		ExcludedSearchWordServiceValidator validator) {
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
