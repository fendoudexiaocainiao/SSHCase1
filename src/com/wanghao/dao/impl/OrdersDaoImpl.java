package com.wanghao.dao.impl;

import com.wanghao.dao.OrdersDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("ordersDao")
public class OrdersDaoImpl extends HibernateDaoSupport implements OrdersDao {

	@Autowired
	private void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
}
