package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelLead;
import com.bookevhotel.core.dao.repository.HotelLeadRepositoryImpl;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelLeadMapper;
import com.bookevhotel.core.validation.HotelLeadServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelLeadService extends AbstractBookEVHotelService<HotelLead, HotelLeadDTO> {
	protected final HotelService hotelService;

	@Autowired
	public HotelLeadService(
		HotelLeadRepositoryImpl repository,
		HotelLeadMapper mapper,
		HotelLeadServiceValidator validator,
		HotelService hotelService) {
		super(repository, mapper, validator);
		this.hotelService = hotelService;
	}

	@Override
	protected void processAfterCreateOne(HotelLeadDTO incomingDTO, HotelLeadDTO newDoc) throws BookEVHotelException {
		// Get the hotel the lead has subscribed to
		var hotelDTO = this.hotelService.findOne(HotelDTO.builder().id(newDoc.getHotelId()).build());

		// Get the list of offers
		var offers = hotelDTO.getOffers();

		// Update language code
		newDoc.setLanguageCode(incomingDTO.getLanguageCode());

		// Send Offer to the subscriber
	}
}
