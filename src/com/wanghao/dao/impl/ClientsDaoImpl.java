package com.wanghao.dao.impl;

import com.wanghao.dao.ClientsDao;
import com.wanghao.domain.Clients;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "clientsDao")
public class ClientsDaoImpl extends HibernateDaoSupport implements ClientsDao {
	@Autowired
	private void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Clients> findAll() {
		return (List<Clients>) getHibernateTemplate().find("from Clients ");
	}

	@Override
	public void addClients(Clients clients) {
		getHibernateTemplate().save(clients);
	}

	@Override
	public void delClients(String cId) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Clients clients = hibernateTemplate.load(Clients.class, cId);
		hibernateTemplate.delete(clients);
	}
}
