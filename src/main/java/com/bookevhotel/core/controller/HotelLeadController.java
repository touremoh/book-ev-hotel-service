package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.BookEVHotelRequestResponse;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.HotelLeadRequestParamsMapper;
import com.bookevhotel.core.service.HotelLeadService;
import com.bookevhotel.core.utils.BuildApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/leads")
public class HotelLeadController extends AbstractBookEVHotelController<HotelLeadDTO> {
	public HotelLeadController(HotelLeadService service, HotelLeadRequestParamsMapper rpm) {
		super(service, rpm);
	}

	@PutMapping(path = "/{hotelId}/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> addLead(
		@RequestBody HotelLeadDTO hotelLeadDTO,
		@PathVariable String hotelId,
		@RequestHeader("languageCode") String languageCode) throws BookEVHotelException {
		hotelLeadDTO.setHotelId(hotelId);
		hotelLeadDTO.setLanguageCode(languageCode);
		return BuildApiResponse.from(this.service.createOne(hotelLeadDTO));
	}

	@GetMapping(path = "/{hotelId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> findAll(@RequestParam Map<String, String> params, @PathVariable String hotelId) throws BookEVHotelException {
		return BuildApiResponse.from(this.service.findAll(HotelLeadDTO.builder().hotelId(hotelId).build(), this.requestMapper.getPage(params)));
	}
}
