package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.mapper.requests.HotelRequestParamsMapper;
import com.bookevhotel.core.service.HotelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController extends AbstractBookEVHotelController<HotelDTO> {
	public HotelController(HotelService service, HotelRequestParamsMapper requestParamsMapper) {
		super(service, requestParamsMapper);
	}
}
