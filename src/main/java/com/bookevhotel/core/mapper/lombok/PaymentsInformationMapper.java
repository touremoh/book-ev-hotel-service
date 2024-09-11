package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.PaymentsInformation;
import com.bookevhotel.core.dto.PaymentsInformationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentsInformationMapper extends BookEVHotelMapper<PaymentsInformation, PaymentsInformationDTO> {
}
