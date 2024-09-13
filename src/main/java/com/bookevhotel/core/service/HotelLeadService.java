package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelLead;
import com.bookevhotel.core.dao.repository.HotelLeadRepositoryImpl;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.mapper.lombok.HotelLeadMapper;
import com.bookevhotel.core.validation.HotelLeadServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelLeadService extends AbstractBookEVHotelService<HotelLead, HotelLeadDTO> {
	@Autowired
	public HotelLeadService(HotelLeadRepositoryImpl repository, HotelLeadMapper mapper, HotelLeadServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
