package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.mapper.requests.HotelUserRequestParamsMapper;
import com.bookevhotel.core.service.HotelUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class HotelUserController extends AbstractBookEVHotelController<HotelUserDTO> {
	public HotelUserController(HotelUserService service, HotelUserRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
