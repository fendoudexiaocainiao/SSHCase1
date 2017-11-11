package com.wanghao.service.impl;

import com.wanghao.dao.OrdersDao;
import com.wanghao.domain.Clients;
import com.wanghao.domain.PageBean;
import com.wanghao.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDao ordersDao = null;

	@Override
	public PageBean<Clients> findOrders(PageBean<Clients> pageBean) {
		ordersDao.findOrders(pageBean);
		pageBean.setMaxRecords(pageBean.getTarget().getOrders().size());
		pageBean.getTarget().setOrders(pageBean.getTarget().getOrders().subList(pageBean.getBeginRecords(), (pageBean.getBeginRecords() + pageBean.getCurrentRecords() <= pageBean.getMaxRecords()) ? pageBean.getBeginRecords() + pageBean.getCurrentRecords() : pageBean.getMaxRecords()));
		return pageBean;
	}
}
