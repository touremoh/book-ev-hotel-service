package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.ActivationCodeDTO;
import com.bookevhotel.core.dto.BookEVHotelRequestResponse;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.HotelUserRequestParamsMapper;
import com.bookevhotel.core.service.HotelUserService;
import com.bookevhotel.core.utils.BuildApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class HotelUserController extends AbstractBookEVHotelController<HotelUserDTO> {

	@Autowired
	public HotelUserController(HotelUserService service, HotelUserRequestParamsMapper rpm) {
		super(service, rpm);
	}

	@PatchMapping(path = "/account/activate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> activate(@RequestBody ActivationCodeDTO activationCode) throws BookEVHotelException {
		return BuildApiResponse.from(((HotelUserService) this.service).activateUserAccount(activationCode));
	}
}
