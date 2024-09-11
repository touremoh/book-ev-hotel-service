package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.PaymentsInformationDTO;
import com.bookevhotel.core.mapper.requests.PaymentsInformationRequestParamsMapper;
import com.bookevhotel.core.service.PaymentsInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments-information")
public class PaymentsInformationController extends AbstractBookEVHotelController<PaymentsInformationDTO> {
	public PaymentsInformationController(PaymentsInformationService service, PaymentsInformationRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
