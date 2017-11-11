package com.wanghao.service;

import com.wanghao.domain.Clients;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Administrator
 * @create 2017-11-10-10:10
 */
public interface ClientsService {
	List<Clients> findAll();

	void addClients(Clients clients);

	void delClients(String cId);

}
