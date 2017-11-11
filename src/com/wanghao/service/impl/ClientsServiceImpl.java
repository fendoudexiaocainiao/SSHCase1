package com.wanghao.service.impl;

import com.wanghao.dao.ClientsDao;
import com.wanghao.domain.Clients;
import com.wanghao.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "clientsService")
public class ClientsServiceImpl implements ClientsService {
	@Autowired
	@Qualifier(value = "clientsDao")
	private ClientsDao clientsDao = null;

	@Transactional(readOnly = true)
	@Override
	public List<Clients> findAll() {
		return clientsDao.findAll();
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public void addClients(Clients clients) {
		clientsDao.addClients(clients);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public void delClients(String cId) {
		clientsDao.delClients(cId);
	}
}
