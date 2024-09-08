package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.MailingListDTO;
import com.bookevhotel.core.service.MailingListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailing-list")
public class MailingListController extends AbstractBookEVHotelController<MailingListDTO> {
	public MailingListController(MailingListService service) {
		super(service);
	}
}
