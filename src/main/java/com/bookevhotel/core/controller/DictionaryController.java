package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.DictionaryDTO;
import com.bookevhotel.core.mapper.requests.DictionaryRequestParamsMapper;
import com.bookevhotel.core.service.DictionaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends AbstractBookEVHotelController<DictionaryDTO> {
	public DictionaryController(DictionaryService service, DictionaryRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
