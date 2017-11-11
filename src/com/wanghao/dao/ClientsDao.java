package com.wanghao.dao;

import com.wanghao.domain.Clients;

import java.util.List;

public interface ClientsDao {
	List<Clients> findAll();

	void addClients(Clients clients);

	void delClients(String cId);

}
