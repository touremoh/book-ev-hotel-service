package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.mapper.requests.SearchWordRequestParamsMapper;
import com.bookevhotel.core.service.SearchWordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends AbstractBookEVHotelController<SearchWordDTO> {
	public DictionaryController(SearchWordService service, SearchWordRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
