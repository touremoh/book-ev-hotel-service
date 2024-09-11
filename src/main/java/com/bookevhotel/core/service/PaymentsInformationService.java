package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.PaymentsInformation;
import com.bookevhotel.core.dao.repository.PaymentsInformationRepository;
import com.bookevhotel.core.dto.PaymentsInformationDTO;
import com.bookevhotel.core.mapper.lombok.PaymentsInformationMapper;
import com.bookevhotel.core.validation.PaymentsInformationServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsInformationService extends AbstractBookEVHotelService<PaymentsInformation, PaymentsInformationDTO> {
	@Autowired
	public PaymentsInformationService(PaymentsInformationRepository repository, PaymentsInformationMapper mapper, PaymentsInformationServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
