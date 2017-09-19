package com.imd.friendsManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.friendsManagement.entity.SendReceivedEmail;
import com.imd.friendsManagement.repository.SendReceivedEmailRepository;
import com.imd.friendsManagement.service.SendReceivedEmailService;

/**
 * 
 * @author Gilang ZW
 *
 */
@Service
public class SendReceivedEmailServiceImpl implements SendReceivedEmailService {

	private final Logger LOGGER = LoggerFactory.getLogger(SendReceivedEmailServiceImpl.class);

	@Autowired
	private SendReceivedEmailRepository sendReceivedEmailRepository;

	@Override
	@Transactional
	public SendReceivedEmail save(SendReceivedEmail sendReceivedEmail) {
		SendReceivedEmail s = new SendReceivedEmail();
		try {
			if (sendReceivedEmail.getId() != null) {
				LOGGER.info("UPDATE DATA WITH ID = {}", sendReceivedEmail.getId());
				return sendReceivedEmailRepository.save(sendReceivedEmail);
			} else {
				LOGGER.info("SAVE DATA WITH EMAIL ADDRESS = {}", sendReceivedEmail.getSender());
				s.setSender(sendReceivedEmail.getSender());
				s.setText(sendReceivedEmail.getText());
				s.setRecipients(sendReceivedEmail.getRecipients());
				s.setStatus(sendReceivedEmail.getStatus());
				s.setCreated(sendReceivedEmail.getCreated());
			}
		} catch (Exception ex) {
			LOGGER.info("error save sendReceivedEmail cause : {}", ex);
		}
		return sendReceivedEmailRepository.save(s);
	}

	@Override
	public SendReceivedEmail getById(Long id) {
		try {
			return sendReceivedEmailRepository.findOne(id);
		} catch (Exception ex) {
			LOGGER.info("error find id sendReceivedEmail cause : {}", ex);
		}
		return null;
	}

	@Override
	public List<SendReceivedEmail> getAll() {
		List<SendReceivedEmail> listOfModel = sendReceivedEmailRepository.findAll();
		try {
			if (!listOfModel.isEmpty()) {
				return listOfModel;
			}
		} catch (Exception ex) {
			LOGGER.info("error find all sendReceivedEmail cause : {}", ex);
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			if (id != null) {
				sendReceivedEmailRepository.delete(id);
			}
		} catch (Exception ex) {
			LOGGER.info("error find id sendReceivedEmail cause : {}", ex);
		}
	}
}