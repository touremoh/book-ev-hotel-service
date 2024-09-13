package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.mapper.requests.HotelLeadRequestParamsMapper;
import com.bookevhotel.core.service.HotelLeadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-leads")
public class HotelLeadController extends AbstractBookEVHotelController<HotelLeadDTO> {
	public HotelLeadController(HotelLeadService service, HotelLeadRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
