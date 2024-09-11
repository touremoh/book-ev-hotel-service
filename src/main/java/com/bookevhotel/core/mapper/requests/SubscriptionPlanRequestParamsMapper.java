package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.annotation.RequestParamsMapper;
import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import com.bookevhotel.core.dto.PaymentsInformationDTO;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.util.Map;

@RequestParamsMapper
public class SubscriptionPlanRequestParamsMapper implements BookEVHotelRequestParamsMapper<SubscriptionPlanDTO> {
	@Override
	public SubscriptionPlanDTO map(Map<String, String> params) throws BookEVHotelException {
		BookEVHotelUtils.assertNotNull(params, "Http request params must not be null");
		
		return null;
	}
}
