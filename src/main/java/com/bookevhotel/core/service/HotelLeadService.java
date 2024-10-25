package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelLead;
import com.bookevhotel.core.dao.repository.HotelLeadRepositoryImpl;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelLeadMapper;
import com.bookevhotel.core.validation.HotelLeadServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelLeadService extends AbstractBookEVHotelService<HotelLead, HotelLeadDTO> {
	protected final HotelService hotelService;
	protected final HotelLeadNotificationService hotelLeadNotificationService;

	@Autowired
	public HotelLeadService(
		HotelLeadRepositoryImpl repository,
		HotelLeadMapper mapper,
		HotelLeadServiceValidator validator,
		HotelService hotelService,
		HotelLeadNotificationService hotelLeadNotificationService) {
		super(repository, mapper, validator);
		this.hotelService = hotelService;
		this.hotelLeadNotificationService = hotelLeadNotificationService;
	}

	@Override
	protected void processBeforeCreateOne(HotelLeadDTO dto) throws BookEVHotelException {
		// Check the hotel id before persist
		var hotelExists = this.hotelService.exists(HotelDTO.builder().id(dto.getHotelId()).build());

		// Check if hotel was found
		if (!hotelExists) {
			log.warn("Cannot find hotel for the lead {}", dto);
			throw new BookEVHotelException(
				"Unable to find the hotel offer",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}

	}

	@Override
	protected void processAfterCreateOne(HotelLeadDTO incomingDTO, HotelLeadDTO newDoc) throws BookEVHotelException {
		// Get Hotel Information
		var hotelDto = this.hotelService.findOne(HotelDTO.builder().id(newDoc.getHotelId()).build());

		// Update newDoc
		newDoc.setLanguageCode(incomingDTO.getLanguageCode());
		newDoc.setRequestedOffer(incomingDTO.getRequestedOffer());

		// Send Offer to the subscriber
		this.hotelLeadNotificationService.sendNotification(newDoc, hotelDto);
	}
}
