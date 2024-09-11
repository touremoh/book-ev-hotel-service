package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.repository.HotelRepository;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.mapper.lombok.HotelMapper;
import com.bookevhotel.core.validation.HotelServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService  extends AbstractBookEVHotelService<Hotel, HotelDTO> {
	@Autowired
	public HotelService(HotelRepository repository, HotelMapper mapper, HotelServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
