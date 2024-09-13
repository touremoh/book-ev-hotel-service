package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.mapper.requests.VisitorSearchRequestParamsMapper;
import com.bookevhotel.core.service.VisitorSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searches")
public class VisitorSearchController extends AbstractBookEVHotelController<VisitorSearchDTO> {
	public VisitorSearchController(VisitorSearchService service, VisitorSearchRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
