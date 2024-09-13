package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.MailingListDTO;
import com.bookevhotel.core.mapper.requests.MailingListRequestParamsMapper;
import com.bookevhotel.core.service.MailingListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailing-list")
public class MailingListController extends AbstractBookEVHotelController<MailingListDTO> {
	public MailingListController(MailingListService service, MailingListRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
