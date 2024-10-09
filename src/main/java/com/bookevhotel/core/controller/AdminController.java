package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.*;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.AdminDashboardRequestParamsMapper;
import com.bookevhotel.core.service.AdminDashboardService;
import com.bookevhotel.core.service.HotelService;
import com.bookevhotel.core.service.HotelUserService;
import com.bookevhotel.core.service.PaymentsInformationService;
import com.bookevhotel.core.utils.BuildApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController extends AbstractBookEVHotelController<AdminDashboardDTO> {
	protected final HotelService hotelService;
	protected final AdminDashboardService adminDashboardService;
	protected final HotelUserService hotelUserService;
	protected final PaymentsInformationService paymentsInformationService;

	public AdminController(
		AdminDashboardService service,
		AdminDashboardRequestParamsMapper requestParamsMapper,
		HotelService hotelService,
		AdminDashboardService adminDashboardService,
		HotelUserService hotelUserService,
		PaymentsInformationService paymentsInformationService) {
		super(service, requestParamsMapper);
		this.hotelService = hotelService;
		this.adminDashboardService = adminDashboardService;
		this.hotelUserService = hotelUserService;
		this.paymentsInformationService = paymentsInformationService;
	}

	// Hotel management
	@PutMapping(path = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> addHotelInformation(@RequestBody HotelDTO hotelDTO) throws BookEVHotelException {
		return BuildApiResponse.from(this.hotelService.createOne(hotelDTO));
	}

	// Payment info management
	@PutMapping(path = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> addHotelPaymentInformation(@RequestBody PaymentsInformationDTO paymentsInformationDTO) throws BookEVHotelException {
		return BuildApiResponse.from(this.paymentsInformationService.createOne(paymentsInformationDTO));
	}

	// Users management
	@PutMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> addNewUser(@RequestBody HotelUserDTO userDTO) throws BookEVHotelException {
		return BuildApiResponse.from(this.hotelUserService.createOne(userDTO));
	}

	// Dashboard management

}
