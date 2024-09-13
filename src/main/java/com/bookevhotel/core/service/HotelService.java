package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.repository.SearchWordRepositoryImpl;
import com.bookevhotel.core.dao.repository.HotelRepositoryImpl;
import com.bookevhotel.core.dao.repository.VisitorSearchRepositoryImpl;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelMapper;
import com.bookevhotel.core.validation.HotelServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HotelService  extends AbstractBookEVHotelService<Hotel, HotelDTO> {
	protected final VisitorSearchRepositoryImpl visitorSearchRepositoryImpl;
	protected final SearchWordRepositoryImpl searchWordRepositoryImpl;
	@Autowired
	public HotelService(HotelRepositoryImpl repository,
						HotelMapper mapper,
						HotelServiceValidator validator,
						VisitorSearchRepositoryImpl visitorSearchRepositoryImpl,
						SearchWordRepositoryImpl searchWordRepositoryImpl) {
		super(repository, mapper, validator);
		this.visitorSearchRepositoryImpl = visitorSearchRepositoryImpl;
		this.searchWordRepositoryImpl = searchWordRepositoryImpl;
	}
}
