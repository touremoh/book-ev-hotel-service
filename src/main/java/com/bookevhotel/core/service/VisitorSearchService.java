package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.VisitorSearch;
import com.bookevhotel.core.dao.repository.VisitorSearchRepository;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.mapper.lombok.VisitorSearchMapper;
import com.bookevhotel.core.validation.VisitorSearchServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorSearchService extends AbstractBookEVHotelService<VisitorSearch, VisitorSearchDTO> {
	@Autowired
	public VisitorSearchService(VisitorSearchRepository repository, VisitorSearchMapper mapper, VisitorSearchServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
