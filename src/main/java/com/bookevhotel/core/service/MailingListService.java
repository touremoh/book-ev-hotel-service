package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.MailingList;
import com.bookevhotel.core.dao.repository.MailingListRepository;
import com.bookevhotel.core.dto.MailingListDTO;
import com.bookevhotel.core.mapper.MailingListMapper;
import org.springframework.stereotype.Service;

@Service
public class MailingListService extends AbstractBookEVHotelService<MailingList, MailingListDTO> {
	public MailingListService(MailingListRepository repository, MailingListMapper mapper) {
		super(repository, mapper);
	}
}
