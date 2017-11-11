package com.wanghao.dao.impl;

import com.wanghao.dao.OrdersDao;
import com.wanghao.domain.Clients;
import com.wanghao.domain.PageBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ordersDao")
public class OrdersDaoImpl extends HibernateDaoSupport implements OrdersDao {

	@Autowired
	private void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public PageBean<Clients> findOrders(PageBean<Clients> pageBean) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		pageBean.setTarget(hibernateTemplate.get(Clients.class, pageBean.getTarget().getcId()));
		return pageBean;
	}
}
