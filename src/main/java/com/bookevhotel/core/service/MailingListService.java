package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.MailingList;
import com.bookevhotel.core.dao.repository.MailingListRepositoryImpl;
import com.bookevhotel.core.dto.MailingListDTO;
import com.bookevhotel.core.mapper.lombok.MailingListMapper;
import com.bookevhotel.core.validation.MailingListServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailingListService extends AbstractBookEVHotelService<MailingList, MailingListDTO> {
	@Autowired
	public MailingListService(MailingListRepositoryImpl repository, MailingListMapper mapper, MailingListServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
