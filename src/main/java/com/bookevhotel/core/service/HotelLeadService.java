package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelLead;
import com.bookevhotel.core.dao.repository.HotelLeadRepository;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.mapper.HotelLeadMapper;
import org.springframework.stereotype.Service;

@Service
public class HotelLeadService extends AbstractBookEVHotelService<HotelLead, HotelLeadDTO> {
	public HotelLeadService(HotelLeadRepository repository, HotelLeadMapper mapper) {
		super(repository, mapper);
	}
}
