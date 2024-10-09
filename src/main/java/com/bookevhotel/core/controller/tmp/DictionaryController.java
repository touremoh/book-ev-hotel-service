package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.SearchKeywordDTO;
import com.bookevhotel.core.mapper.requests.SearchKeywordRequestParamsMapper;
import com.bookevhotel.core.service.SearchKeywordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends AbstractBookEVHotelController<SearchKeywordDTO> {
	public DictionaryController(SearchKeywordService service, SearchKeywordRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
