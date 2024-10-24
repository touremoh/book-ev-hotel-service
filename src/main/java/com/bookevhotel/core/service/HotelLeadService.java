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
	protected void processAfterCreateOne(HotelLeadDTO incomingDTO, HotelLeadDTO newDoc) throws BookEVHotelException {
		// Update newDoc
		newDoc.setLanguageCode(incomingDTO.getLanguageCode());
		newDoc.setRequestedOffer(incomingDTO.getRequestedOffer());

		// Send Offer to the subscriber
		this.hotelLeadNotificationService.sendNotification(newDoc);
	}
}
