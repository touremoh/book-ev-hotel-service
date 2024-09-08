package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.repository.HotelRepository;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.mapper.HotelMapper;
import org.springframework.stereotype.Service;

@Service
public class HotelService  extends AbstractBookEVHotelService<Hotel, HotelDTO> {
	public HotelService(HotelRepository repository, HotelMapper mapper) {
		super(repository, mapper);
	}
}
