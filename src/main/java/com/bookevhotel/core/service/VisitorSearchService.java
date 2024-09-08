package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.VisitorSearch;
import com.bookevhotel.core.dao.repository.VisitorSearchRepository;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.mapper.VisitorSearchMapper;
import org.springframework.stereotype.Service;

@Service
public class VisitorSearchService extends AbstractBookEVHotelService<VisitorSearch, VisitorSearchDTO> {
	public VisitorSearchService(VisitorSearchRepository repository, VisitorSearchMapper mapper) {
		super(repository, mapper);
	}
}
