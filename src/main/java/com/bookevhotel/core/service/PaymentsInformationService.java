package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.PaymentsInformation;
import com.bookevhotel.core.dao.repository.PaymentsInformationRepository;
import com.bookevhotel.core.dto.PaymentsInformationDTO;
import com.bookevhotel.core.mapper.PaymentsInformationMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentsInformationService extends AbstractBookEVHotelService<PaymentsInformation, PaymentsInformationDTO> {
	public PaymentsInformationService(PaymentsInformationRepository repository, PaymentsInformationMapper mapper) {
		super(repository, mapper);
	}
}
