package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.annotation.RequestParamsMapper;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.dto.UserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.util.Map;

@RequestParamsMapper
public class UserRequestParamsMapper implements BookEVHotelRequestParamsMapper<UserDTO> {
	@Override
	public UserDTO map(Map<String, String> params) throws BookEVHotelException {
		BookEVHotelUtils.assertNotNull(params, "Http request params must not be null");
		
		return null;
	}
}
